package com.stefli.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CodeGenerator {

	private static final Logger logger = Logger.getLogger(CodeGenerator.class
			.getName());

	// Database configuration
	private static String jdbcUrl;
	private static String jdbcUser;
	private static String jdbcPassword;
	private static String jdbcDriver;

	// Module configuration
	private static String pkg = "test";
	private static String tableName = "test";

	static {
		try {
			InputStream in = CodeGenerator.class
					.getResourceAsStream("/app.properties");
			Properties prop = new Properties();
			prop.load(in);
			jdbcUrl = prop.getProperty("jdbc.url");
			jdbcUser = prop.getProperty("jdbc.user");
			jdbcPassword = prop.getProperty("jdbc.password");
			jdbcDriver = prop.getProperty("jdbc.driver");
			pkg = prop.getProperty("module.pkg");
			tableName = prop.getProperty("module.table");
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	public static void main(String[] args) {
		String entityName = generateCamelStyleName(tableName);
		List<Map<String, String>> columns = retrieveTableInfo(tableName);
		generate(pkg, entityName, columns);
	}

	/**
	 * Generate entity name by table name
	 * 
	 * @param tableName
	 * @return
	 */
	private static String generateCamelStyleName(String tableName) {
		// Return table name as entity name if no underline found.
		if (tableName.indexOf("_") == -1) {
			return tableName;
		}

		// Generate entity name
		StringBuilder builder = new StringBuilder();
		String[] tmp = tableName.split("_");
		char firstLetter = tmp[0].charAt(0);
		builder.append(String.valueOf(firstLetter).toUpperCase());
		builder.append(tmp[0].substring(1));

		int len = tmp.length;
		for (int i = 1; i < len; i++) {
			firstLetter = tmp[i].charAt(0);
			builder.append(String.valueOf(firstLetter).toUpperCase());
			builder.append(tmp[i].substring(1));
		}
		return builder.toString();
	}

	/**
	 * Retrieve the table schema information
	 * 
	 * @param tableName
	 */
	private static List<Map<String, String>> retrieveTableInfo(String tableName) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try {
			Class.forName(jdbcDriver).newInstance();
			Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser,
					jdbcPassword);
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getColumns(conn.getCatalog(), null, tableName,
					null);
			while (rs.next()) {
				String columnName = generateCamelStyleName(rs
						.getString("COLUMN_NAME"));
				String columnType = rs.getString("DATA_TYPE");
				String columnTypeName = rs.getString("TYPE_NAME");
				String dataSize = rs.getString("COLUMN_SIZE");
				String digits = rs.getString("DECIMAL_DIGITS");
				String nullable = rs.getString("NULLABLE");
				Map<String, String> map = new HashMap<String, String>();
				map.put("columnName", columnName);
				map.put("columnType", columnType);
				map.put("columnTypeName", columnTypeName);
				map.put("dataSize", dataSize);
				map.put("digits", digits);
				map.put("nullable", nullable);
				result.add(map);
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.getLocalizedMessage(), e);
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		} catch (InstantiationException e) {
			logger.error(e.getLocalizedMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return result;
	}

	/**
	 * Generate the source code
	 * 
	 * @param pkg
	 * @param entityName
	 * @param tableInfo
	 */
	public static void generate(String pkg, String entityName,
			List<Map<String, String>> columns) {
		if (Strings.isNullOrEmpty(pkg)) {
			throw new IllegalArgumentException("Empty package!");
		}
		if (Strings.isNullOrEmpty(entityName)) {
			throw new IllegalArgumentException("Empty entity name!");
		}
		String[] templates = new String[] { "Dao", "DaoImpl", "Entity",
				"Service", "ServiceImpl", "View", "Controller", "Mapper" };
		String[] pkgs = new String[] { "repository", "repository.impl",
				"entity", "service", "service.impl", "view", "controller",
				"xmlmapper" };
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
			cfg.setDirectoryForTemplateLoading(new File("template"));
			cfg.setDefaultEncoding("UTF-8");

			// Clear output
			File output = new File("output");
			if (output.exists()) {
				delete("output");
			}
			output.mkdir();
			Template template = null;
			int len = templates.length;
			for (int i = 0; i < len; i++) {
				// Get template
				template = cfg.getTemplate(templates[i] + ".ftl");

				// Prepare model data
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("columns", columns);
				model.put("pkg", pkg);
				model.put("entityName", entityName);

				// Get package
				String currentPkg = (pkg + "." + pkgs[i].toLowerCase());
				String pkgFolder = currentPkg.replaceAll("\\.", "/");

				// Create folder
				String filePath = "output" + File.separator + pkgFolder;
				File file = new File(filePath);
				if (!file.exists()) {
					file.mkdirs();
				}

				// Prepare file name
				String fileName = entityName + templates[i].split("\\.")[0]
						+ ".java";
				if (templates[i].indexOf("View") != -1) {
					fileName = entityName + ".view.xml";
				}
				if (templates[i].indexOf("Mapper") != -1) {
					fileName = entityName + templates[i].split("\\.")[0]
							+ ".xml";
				}

				// Generator file
				Writer out = new FileWriter(new File(filePath + File.separator
						+ fileName));
				template.process(model, out);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
		} catch (TemplateException e) {
			logger.error(e.getLocalizedMessage(), e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * Delete folder and files
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void delete(String filePath) throws IOException {
		File f = new File(filePath);
		if (f.exists() && f.isDirectory()) {
			if (f.listFiles().length == 0) {
				f.delete();
			} else {
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						delete(delFile[j].getAbsolutePath());
					}
					delFile[j].delete();
				}
			}
		}
	}

}

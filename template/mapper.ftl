<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${pkg!}.${entityName!}EntityMapper" >
  <resultMap id="BaseResultMap" type="${pkg!}.entity.${entityName!}Entity" >

    <id column="id" property="id" jdbcType="LONG" />
    <#list columns as column>
	<result column="${column.columnName!}" property="${column.columnName!}" jdbcType="${column.columnTypeName!}" />
	</#list>
  </resultMap>
</mapper>
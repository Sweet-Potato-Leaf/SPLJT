package com.splto.dp.model.pojo;

import com.splto.utils.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JsonTypeHandlerAbstract<T> extends BaseTypeHandler<T> implements Serializable {

    protected final Class<T> clazz;

    public JsonTypeHandlerAbstract(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("javaType argument cannot be null");
        }
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, this.toJson(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName), clazz);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getString(columnIndex), clazz);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getString(columnIndex), clazz);
    }

    public abstract T toObject(String content, Class<T> clazz);

    private String toJson(T object) {
        try {
            return JsonUtil.toJson(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

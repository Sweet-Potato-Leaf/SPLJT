package com.splto.dp.model.pojo;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDHexGenerator;
import java.io.Serializable;

public class CustomUUIDHexGenerator extends UUIDHexGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // 尝试从实体对象中获取手动设置的 ID
        Serializable id = session.getEntityPersister(null, object)
                .getClassMetadata()
                .getIdentifier(object, session);

        // 如果 ID 已被手动设置，直接使用它
        if (id != null && !id.toString().isEmpty()) {
            return id;
        }

        // 否则调用父类方法生成 UUID
        return super.generate(session, object);
    }
}

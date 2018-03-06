package se.pensionsmyndigheten.se.tvattomat.domain;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import oracle.xdb.XMLType;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExcerptUserType  implements UserType{
    private XmlMapper xmlMapper = new XmlMapper();

//    {
//        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
//    }

    public ExcerptUserType()  {
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { XMLType._SQL_TYPECODE };
    }

    @Override
    public Class returnedClass() {
        return Excerpt.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return (x != null) && x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return (x != null) ? x.hashCode() : 0;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object excerpt) throws HibernateException, SQLException {
        XMLType xmlType = (XMLType) resultSet.getObject(names[0]);

        Excerpt document = null;
        if (xmlType != null) {
            try {
                StringWriter writer = new StringWriter();
                document = xmlMapper.readValue(xmlType.getBinaryStream(), Excerpt.class);
            } catch (IOException e) {
                throw new SQLException("Could not unmarshal Order", e);
            }
        }

        return document;
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
            try {
                XMLType xmlType = null;
                if (value != null) {
                    NativeJdbcExtractor extractor = new OracleJdbc4NativeJdbcExtractor();
                    Connection connection = extractor.getNativeConnection(statement.getConnection());
                    // Using a helper method, we marshal our Order JAXB instance to its String representation and use that to create a oracle.xdb.XMLType instance
                    xmlType = new XMLType(connection, xmlMapper.writeValueAsString(value));
                }

                // Important to still set object even if it's null
                // to prevent "org.h2.jdbc.JdbcSQLException: Parameter "#?" is not set; SQL statement"
                statement.setObject(index, xmlType);
            } catch (Exception e) {
                throw new SQLException("Could not marshal Order", e);
            }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object excerpt) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object excerpt) throws HibernateException {
        return original;
    }
}

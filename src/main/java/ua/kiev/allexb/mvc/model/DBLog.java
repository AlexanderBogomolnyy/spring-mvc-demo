package ua.kiev.allexb.mvc.model;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class DBLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idLog;
    private String logString;

    public DBLog() {
    }

    public DBLog(String logString) {
        this.logString = logString;
    }

    public DBLog (int idLog, String logString) {
        this(logString);
        this.idLog = idLog;
    }

    public int getIdLog() {
        return idLog;
    }

    @XmlElement
    public void setIdLog(int iDLOG) {
        idLog = iDLOG;
    }

    public String getLogString() {
        return logString;
    }

    @XmlElement
    public void setLogString(String lOGSTRING) {
        logString = lOGSTRING;
    }

    @Override
    public String toString() {
        return "DBLog{" +
                "ID : " + idLog +
                "; LOG : " + logString + '}';
    }
}
package com.revature.util;

public class ConnFactory{
  private static ConnFactory cf = new ConnFactory();

  private ConnFactory(){
    super();
  }
  public static synchronized ConnFactory getInstance(){
    if (cf == null){
      cf = new ConnFactory();
    }
    return cf;
  }
public Connection getConnection(){
  Connection conn = null;
  // Don't hardcode url, usr, pw

  Properties prop = new Properties();
  try{
  prop.load(new FileReader("database.properties"));
Class.forName(prop.getProperty("driver"));
conn = DriverManger.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("pass"))

}catch (IOException e){
  e.printStackTrace();
}
return conn;
}
}

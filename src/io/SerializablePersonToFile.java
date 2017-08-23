package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Datartvinci on 2017/1/8.
 */
public class SerializablePersonToFile {
    class Person implements Serializable {
        private static final long serialVersionUID = -3906495353774513550L;
        private String name = "";
        private String age = "";
        private String personId = "";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }

        public String getCellPhoneNo() {
            return cellPhoneNo;
        }

        public void setCellPhoneNo(String cellPhoneNo) {
            this.cellPhoneNo = cellPhoneNo;
        }

        private String cellPhoneNo = "";
    }

    private List<Person> initList() {
        List<Person> userList = new ArrayList<>();
        Person loginUser = new Person();
        loginUser.setName("sam");
        loginUser.setAge("30");
        loginUser.setCellPhoneNo("13333333333");
        loginUser.setPersonId("111111111111111111");
        userList.add(loginUser);
        loginUser = new Person();
        loginUser.setName("tonny");
        loginUser.setAge("31");
        loginUser.setCellPhoneNo("14333333333");
        loginUser.setPersonId("111111111111111111");
        userList.add(loginUser);
        loginUser = new Person();
        loginUser.setName("jim");
        loginUser.setAge("28");
        loginUser.setCellPhoneNo("15333333333");
        loginUser.setPersonId("111111111111111111");
        userList.add(loginUser);
        loginUser = new Person();
        loginUser.setName("Simon");
        loginUser.setAge("30");
        loginUser.setCellPhoneNo("17333333333");
        loginUser.setPersonId("111111111111111111");
        userList.add(loginUser);
        return userList;
    }

    private void serializeFromFile() {
        FileInputStream fs = null;
        ObjectInputStream ois = null;
        try {
            fs = new FileInputStream("person.txt");
            ois = new ObjectInputStream(fs);
            List<Person> userList = (ArrayList<Person>) ois.readObject();
            for (Person p : userList) {
                System.out.println(p.getName() + "   " + p.getAge() + "   "
                        + p.getCellPhoneNo() + "   " + p.getCellPhoneNo());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
            }
            try {
                if (fs != null) {
                    fs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void serializeToFile() {
        FileOutputStream fs = null;
        ObjectOutputStream os = null;
        try {
            fs = new FileOutputStream("person.txt");
            os = new ObjectOutputStream(fs);
            Person loginUser = new Person();
            loginUser.setName("jim");
            loginUser.setAge("28");
            loginUser.setCellPhoneNo("15333333333");
            loginUser.setPersonId("111111111111111111");
            os.writeObject(loginUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
            }
            try {
                if (fs != null) {
                    fs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        SerializablePersonToFile sf = new SerializablePersonToFile();
        sf.serializeToFile();
        sf.serializeFromFile();
    }
}

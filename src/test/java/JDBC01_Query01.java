import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class JDBC01_Query01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1- İlgili Drive'i yuklemeliyiz -MySQL kullandığımızı bildiriyoruz
        // Driver'ı bulamama ihtimaline karsi forrName methodu benden ClassNotFoundException
        //icin main methoda exception fırlatmamı istiyor

        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2-Baglantiyi olusturmak icin username ve password girisi yapmalıyız
        // burada da username ve passwordun yanlış olma ihtimaline karsi getconnection methodu
        // SQLException fırlatmanı istiyor

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "17187434");

        // // 3 - SQL Query'leri icin bir Statement objesi olusturup , javada kendimize SQL sorgulari icin bir alan acacagiz.

        Statement st = con.createStatement();

        // 4- SQL sorgularını yazip, calıstırabiliriz

        ResultSet veri = st.executeQuery("SELECT * FROM calisanlar"); //executeQuery komutunu select komutu için kullanıyoruz

        //5- Sonucları gormek icin Iteraton le Set içerisindeki elemanları while dongusu icerisnde yazdıracağız

        // System.out.println(veri); // referans döndürür
            /*
            CREATE TABLE calisanlar(
		    id INT PRIMARY KEY,
		    isim VARCHAR(50),
		    sehir VARCHAR(50),
		    maas INT,
		    sirket VARCHAR(20)
	        );
             */
/*
        while (veri.next()) {

            System.out.println( veri.getInt("id") + "-"+   veri.getString("isim") + "-" +  veri.getString("sehir")+ "-"+
                     veri.getInt("maas") + "-"+  veri.getString("sirket"));
        }
*/
        while (veri.next()) {

            System.out.println(veri.getInt("1") + "-" + veri.getString("2") + "-" + veri.getString("3") + "-" +
                    veri.getInt("4") + "-" + veri.getString("5"));

        }
        // 6 - Olusturulan nesneleri close() ile kapatiyoruz ki bellekten kaldiralim

        con.close();
        st.close();
        veri.close();


    }
  }


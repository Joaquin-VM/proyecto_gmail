import com.gmail.conf.JDBCUtil;
import com.gmail.dao.CorreoDAO;
import com.gmail.dao.UsuarioDAO;
import com.gmail.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

  public static void main(String[] args) {
//    LocalDateTime ld = LocalDateTime.now();
//    System.out.println(ld.getHour() + " " + ld.getMinute() + " " + ld.getSecond());
//    LocalDate localDate = LocalDate.of(2021, 8, 3);
//    LocalDate localDate2 = LocalDate
//        .of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
//    System.out.println(localDate2);

    UsuarioDAO.addUsuario(
        new Usuario().setNombre("Pedro2").setApellido("Jejox2").setCorreo("correo22@gmail.com")
            .setContrasenia("contrasenia22").setTelefono("+54 9 32412331232")
            .setFechaNacimiento(LocalDate.of(2020, 5, 7)).setSexo("Masculino"));

  }
}

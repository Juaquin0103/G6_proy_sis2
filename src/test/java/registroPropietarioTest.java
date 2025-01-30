/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.proyectolavadero.Backend.registroPropietario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kateS
 */
public class registroPropietarioTest {
    
    private final registroPropietario instance = new registroPropietario();


    @Test
    public void testLongitudNombre() {
        String nombreValido = "Sebastian Ruiz";
        assertTrue(instance.validarNombre(nombreValido), "El nombre con longitud válida debería ser aceptado");

        String nombreLargo = "Sebastian Dominguez Perez";
        assertFalse(instance.validarNombre(nombreLargo), "El nombre con más de 20 caracteres debería ser rechazado");
    }

    @Test
    public void testNombreConNumeros() {
        String nombreConNumeros = "Sebastian123 Ruiz";
        assertFalse(instance.validarNombre(nombreConNumeros), "El nombre con números debería ser rechazado");

        String nombreSinNumeros = "Sebastian Ruiz";
        assertTrue(instance.validarNombre(nombreSinNumeros), "El nombre sin números debería ser aceptado");
        
    }


    @Test
    public void testNombreMayusculaInicial() {
        String nombreValido = "Sebastian Ruiz";
        assertTrue(instance.validarNombre(nombreValido), "El nombre que comienza con mayúscula debería ser aceptado");

        String nombreMinuscula = "sebastian Ruiz";
        assertFalse(instance.validarNombre(nombreMinuscula), "El nombre que comienza con minúscula debería ser rechazado");
        
    }
    
    @Test
    public void testValidarCILongitud() {
         
        String ciLargo = "12345678901";
        assertFalse(instance.validarCI(ciLargo), "El CI con más de 10 caracteres debería ser rechazado");

        String ciValido = "1234567890";
        assertTrue(instance.validarCI(ciValido), "El CI con exactamente 10 caracteres debería ser aceptado");
    }

    @Test
    public void testValidarCINumerico() {
        String ciConLetras = "12345A7890";
        assertFalse(instance.validarCI(ciConLetras), "El CI con letras debería ser rechazado");

        String ciNumerico = "1234567890";
        assertTrue(instance.validarCI(ciNumerico), "El CI solo numérico debería ser aceptado");
    }

    @Test
    public void testValidarCINoVacio() {
        String ciVacio = "";
        assertFalse(instance.validarCI(ciVacio), "El CI vacío debería ser rechazado");

        String ciNulo = null;
        assertFalse(instance.validarCI(ciNulo), "El CI nulo debería ser rechazado");
    }
    
    @Test
    public void testValidarTelefonoLongitud() {
        
        String telefonoLargo = "123456789";
        assertFalse(instance.validarTelefono(telefonoLargo), "El teléfono con más de 8 caracteres debería ser rechazado");

        String telefonoValido = "12345678";
        assertTrue(instance.validarTelefono(telefonoValido), "El teléfono con exactamente 8 caracteres debería ser aceptado");
    }

    @Test
    public void testValidarTelefonoNumerico() {
        String telefonoConLetras = "12345A78";
        assertFalse(instance.validarTelefono(telefonoConLetras), "El teléfono con letras debería ser rechazado");

        String telefonoNumerico = "12345678";
        assertTrue(instance.validarTelefono(telefonoNumerico), "El teléfono solo numérico debería ser aceptado");
    }

    @Test
    public void testValidarTelefonoNoVacio() {
        String telefonoVacio = "";
        assertFalse(instance.validarTelefono(telefonoVacio), "El teléfono vacío debería ser rechazado");

        String telefonoNulo = null;
        assertFalse(instance.validarTelefono(telefonoNulo), "El teléfono nulo debería ser rechazado");
    }
    
    @Test
    public void testValidarDireccionLongitud() {
       
        String direccionExacta = "Calle Principal, Edificio Moderno No. 101";
        assertTrue(instance.validarDireccion(direccionExacta), "La dirección con exactamente 50 caracteres debería ser aceptada");

        String direccionLarga = "Esta es una dirección extremadamente larga que excede los 50 caracteres permitidos";
        assertFalse(instance.validarDireccion(direccionLarga), "La dirección con más de 50 caracteres debería ser rechazada");
    }
    
    @Test
    public void testValidarCorreoFormato() {
        String correoSinArroba = "usuariodominio.com";
        assertFalse(instance.validarCorreo(correoSinArroba), "El correo sin '@' debería ser rechazado");

        String correoFormatoValido = "usuario@dominio.com";
        assertTrue(instance.validarCorreo(correoFormatoValido), "El correo con formato válido debería ser aceptado");
        
    }

    @Test
    public void testValidarCorreoLongitud() {
        String correoLargo = "usuario.nombre.extremadamente.extenso@dominioincreiblementegrande.com";
        assertFalse(instance.validarCorreo(correoLargo), "El correo que excede los 50 caracteres debería ser rechazado");
    }
}





/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.proyectolavadero.Backend.registroVehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class registroVehiculoTest {

    private registroVehiculo instance;

    @BeforeEach
    public void setUp() {
        instance = new registroVehiculo();  // Asegúrate de que se crea una nueva instancia antes de cada prueba
    }

    // Test para validar formato de la placa.
    
    @Test
    public void testValidarPlacaAlfanumerica() {
        System.out.println("Test: validarPlacaAlfanumerica");

        // Formato valido de placa
        String placaValida = "ABC1234";
        boolean resultadoValido = instance.validarPlaca(placaValida);
        assertTrue(resultadoValido, "La placa con caracteres alfanuméricos debe ser valido");

        // Formato invalido con caracteres especiales
        String placaInvalidaEspecial = "ABC@432!";
        boolean resultadoInvalidoEspecial = instance.validarPlaca(placaInvalidaEspecial);
        assertFalse(resultadoInvalidoEspecial, "La placa con caracteres especiales debe ser invalido");

        // Formato invalido con espacio
        String placaInvalidaEspacio = "ABC 1234";
        boolean resultadoInvalidoEspacio = instance.validarPlaca(placaInvalidaEspacio);
        assertFalse(resultadoInvalidoEspacio, "La placa con espacios debe ser invalido");
    }

    
    // Test de longitud máxima de la placa (8 caracteres).
    
    @Test
    public void testLongitudMaximaPlaca() {
        System.out.println("Test: longitudMaximaPlaca");

        // Longitud valida (7 caracteres)
        String placaValida = "ABC1234";
        boolean resultadoValido = instance.validarPlaca(placaValida);
        assertTrue(resultadoValido, "La placa con 7 caracteres debe ser valido");

        // Longitud excedida (más de 8 caracteres)
        String placaExcedida = "ABC123456";
        boolean resultadoExcedida = instance.validarPlaca(placaExcedida);
        assertFalse(resultadoExcedida, "La placa con más de 8 caracteres debe ser invalido");
    }

    // Test de validacion de longitud máxima para el chasis (debe tener 17 caracteres).
    
    @Test
    public void testLongitudMaximaChasis() {
        System.out.println("Test: longitudMaximaChasis");

        // Chasis valido (17 caracteres)
        String chasisValido = "1HGCM82633A123456";  // 17 caracteres
        boolean resultadoValido = instance.validarChasis(chasisValido);
        assertTrue(resultadoValido, "El chasis con 17 caracteres debe ser valido");

        // Chasis invalido (más de 17 caracteres)
        String chasisExcedido = "1HGCM82633A1234567";  // 18 caracteres
        boolean resultadoExcedido = instance.validarChasis(chasisExcedido);
        assertFalse(resultadoExcedido, "El chasis con más de 17 caracteres debe ser invalidoo");
    }

    //  Test de validacinn del color (solo letras).

    @Test
    public void testValidarColor() {
        System.out.println("Test: validarColor");

        // Color valido (solo letras)
        String colorValido = "Rojo";
        boolean resultadoColorValido = instance.validarColor(colorValido);
        assertTrue(resultadoColorValido, "El color con solo letras debe ser valido");

        // Color invalido (con numeros)
        String colorInvalido = "Rojo123";
        boolean resultadoColorInvalido = instance.validarColor(colorInvalido);
        assertFalse(resultadoColorInvalido, "El color con numeros debe ser invalido");

        // Color invalido (con caracteres especiales)
        String colorInvalidoEspecial = "Rojo@";
        boolean resultadoColorInvalidoEspecial = instance.validarColor(colorInvalidoEspecial);
        assertFalse(resultadoColorInvalidoEspecial, "El color con caracteres especiales debe ser invalido");
    }
    //  Test de validacion del Modelo.
    @Test
    public void testValidarModelo() {
        System.out.println("Test: validarModelo");

        // Modelo valido (solo letras y numeros)
        String modeloValido = "Chevrolet Silverado";
        boolean resultadoModeloValido = instance.validarModelo(modeloValido);
        assertTrue(resultadoModeloValido, "El modelo con solo letras y numeros debe ser valido");

        // Modelo invalido (vacio)
        String modeloInvalido = "";
        boolean resultadoModeloInvalido = instance.validarModelo(modeloInvalido);
        assertFalse(resultadoModeloInvalido, "El modelo vacio debe ser invalido");

        // Modelo invalido (nulo)
        String modeloInvalidoNull = null;
        boolean resultadoModeloInvalidoNull = instance.validarModelo(modeloInvalidoNull);
        assertFalse(resultadoModeloInvalidoNull, "El modelo nulo debe ser invalido");
    }
    
    //Test de Validadcion de Marca
    @Test
    public void testValidarMarca() {
        System.out.println("Test: validarMarca");

        // Marca valida (solo letras) en el campo Marca
        String marcaValida = "Chevrolet";
        boolean resultadoMarcaValida = instance.validarMarca(marcaValida);
        assertTrue(resultadoMarcaValida, "La marca con solo letras debe ser valido");

        // Marca invalida (campo vacío)
        String marcaInvalida = "";
        boolean resultadoMarcaInvalida = instance.validarMarca(marcaInvalida);
        assertFalse(resultadoMarcaInvalida, "La marca vacía debe ser invalido");

        // Marca invalida (nula)
        String marcaInvalidaNull = null;
        boolean resultadoMarcaInvalidaNull = instance.validarMarca(marcaInvalidaNull);
        assertFalse(resultadoMarcaInvalidaNull, "La marca nula debe ser invalido");
    }
    // Test de Validacion de Id de Cliente(Propietario) 
    @Test
    public void testValidarIdPropietario() {
        System.out.println("Test: validarIdPropietario");

        // ID del propietario valido (ID que existe en la base de datos)
        String idValido = "1"; // Este ID debe existir en la base de datos
        boolean resultadoIdValido = instance.validarIdCliente(idValido);
        assertTrue(resultadoIdValido, "El ID del propietario que existe debería ser válido");

        // ID del propietario invalido (ID que no existe en la base de datos)
        String idInvalido = "999"; // Este ID no debe existir en la base de datos
        boolean resultadoIdInvalido = instance.validarIdCliente(idInvalido);
        assertFalse(resultadoIdInvalido, "El ID del propietario que no existe debería ser inválido");

        // ID del propietario invalido (nulo)
        String idInvalidoNull = null;
        boolean resultadoIdInvalidoNull = instance.validarIdCliente(idInvalidoNull);
        assertFalse(resultadoIdInvalidoNull, "El ID del propietario nulo debería ser inválido");
    }
}

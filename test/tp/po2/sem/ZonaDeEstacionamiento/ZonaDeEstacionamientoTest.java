package tp.po2.sem.ZonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public class ZonaDeEstacionamientoTest {

    private ZonaDeEstacionamiento zonaDeEstacionamiento;
    private Inspector inspectorMock;
    private Set<PuntoDeVenta> puntosDeVenta;

    @BeforeEach
    public void setUp() {
        inspectorMock = mock(Inspector.class);
        puntosDeVenta = new HashSet<>();

        zonaDeEstacionamiento = new ZonaDeEstacionamiento("Zona 1", inspectorMock, puntosDeVenta);
    }

    @Test
    public void testConstructor() {
        assertNotNull(zonaDeEstacionamiento);
        assertEquals("Zona 1", zonaDeEstacionamiento.getIdentificardorDeZona());
        assertEquals(inspectorMock, zonaDeEstacionamiento.getInspectorAsignado());
        assertEquals(puntosDeVenta, zonaDeEstacionamiento.getPuntosDeVenta());
    }

    @Test
    public void testSetIdentificardorDeZona() {
        zonaDeEstacionamiento.setIdentificardorDeZona("Nueva Zona");
        assertEquals("Nueva Zona", zonaDeEstacionamiento.getIdentificardorDeZona());
    }

    @Test
    public void testSetInspectorAsignado() {
        Inspector nuevoInspectorMock = mock(Inspector.class);
        zonaDeEstacionamiento.setInspectorAsignado(nuevoInspectorMock);
        assertEquals(nuevoInspectorMock, zonaDeEstacionamiento.getInspectorAsignado());
    }

    @Test
    public void testSetPuntosDeVenta() {
        Set<PuntoDeVenta> nuevosPuntosDeVenta = new HashSet<>();
        PuntoDeVenta puntoDeVentaMock1 = mock(PuntoDeVenta.class);
        PuntoDeVenta puntoDeVentaMock2 = mock(PuntoDeVenta.class);
        nuevosPuntosDeVenta.add(puntoDeVentaMock1);
        nuevosPuntosDeVenta.add(puntoDeVentaMock2);

        zonaDeEstacionamiento.setPuntosDeVenta(nuevosPuntosDeVenta);
        assertEquals(nuevosPuntosDeVenta, zonaDeEstacionamiento.getPuntosDeVenta());
    }

    @Test
    public void testAgregarPuntoDeVenta() {
        PuntoDeVenta puntoDeVentaMock = mock(PuntoDeVenta.class);
        zonaDeEstacionamiento.agregarPuntoDeVenta(puntoDeVentaMock);

        assertTrue(zonaDeEstacionamiento.getPuntosDeVenta().contains(puntoDeVentaMock));
    }

    @Test
    public void testCantidadDePuntosDeVenta() {
        assertEquals(0, zonaDeEstacionamiento.cantidadDePuntosDeVenta());

        PuntoDeVenta puntoDeVentaMock1 = mock(PuntoDeVenta.class);
        PuntoDeVenta puntoDeVentaMock2 = mock(PuntoDeVenta.class);
        zonaDeEstacionamiento.agregarPuntoDeVenta(puntoDeVentaMock1);
        zonaDeEstacionamiento.agregarPuntoDeVenta(puntoDeVentaMock2);

        assertEquals(2, zonaDeEstacionamiento.cantidadDePuntosDeVenta());
    }

    @Test
    public void testRemoverPuntoDeVenta() {
        PuntoDeVenta puntoDeVentaMock = mock(PuntoDeVenta.class);
        zonaDeEstacionamiento.agregarPuntoDeVenta(puntoDeVentaMock);

        zonaDeEstacionamiento.removerPuntoDeVenta(puntoDeVentaMock);
        assertTrue(zonaDeEstacionamiento.getPuntosDeVenta().isEmpty());
    }
}

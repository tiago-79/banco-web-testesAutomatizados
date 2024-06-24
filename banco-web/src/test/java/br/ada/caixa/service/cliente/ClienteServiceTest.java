package br.ada.caixa.service.cliente;

import br.ada.caixa.dto.request.RegistrarClientePFRequestDto;
import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.RegistrarClienteResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.TipoCliente;
import br.ada.caixa.enums.StatusCliente;
import br.ada.caixa.respository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ClienteService service;

    @Test
    @DisplayName("teste de registrar clientePF")
    void registrarPFTeste() {
        // given
//        final RegistrarClienteResponseDto expected = mock(RegistrarClienteResponseDto.class);
        final RegistrarClientePFRequestDto clienteDto = mock(RegistrarClientePFRequestDto.class);
        final var cliente = mock(Cliente.class);
        final var docCliente= "1234567890";

        given(cliente.getDocumento()).willReturn(docCliente);
        given(modelMapper.map(clienteDto, Cliente.class)).willReturn(cliente);
        given(clienteRepository.save(cliente)).willReturn(cliente);

        // when
        RegistrarClienteResponseDto actual = service.registrarPF(clienteDto);

        // then
        verify(clienteRepository).save(cliente);
        assertNotNull(actual.getSaldoResponseDto());
        assertEquals(docCliente,actual.getDocumento());
        assertInstanceOf(RegistrarClienteResponseDto.class, actual);
    }

    @Test
    @DisplayName("teste de registrar clientePJ")
    void registrarPJTeste() {
        // given
        final RegistrarClienteResponseDto expected = mock(RegistrarClienteResponseDto.class);
        final RegistrarClientePJRequestDto clienteDto = mock(RegistrarClientePJRequestDto.class);
        final var cliente = mock(Cliente.class);

        given(modelMapper.map(clienteDto, Cliente.class)).willReturn(cliente);
        given(clienteRepository.save(cliente)).willReturn(cliente);

        // when
        RegistrarClienteResponseDto actual = service.registrarPJ(clienteDto);

        // then
        verify(clienteRepository).save(cliente);
        assertNotNull(actual.getSaldoResponseDto());
    }

    @Test
    @DisplayName("teste de registrar cliente")
    void registrarTeste() {
        // given
        final RegistrarClienteResponseDto expected = mock(RegistrarClienteResponseDto.class);
//        final RegistrarClienteResponseDto clienteDto = mock(RegistrarClientePFRequestDto.class);
        final var cliente = mock(Cliente.class);

//        given(modelMapper.map(clienteDto, Cliente.class)).willReturn(cliente);
        given(clienteRepository.save(cliente)).willReturn(cliente);

        // when
        RegistrarClienteResponseDto actual = service.registrar(cliente);

        // then
        verify(clienteRepository).save(cliente);
        assertNotNull(actual.getSaldoResponseDto());
    }

    @Test
    @DisplayName("teste de listar todos com parâmetros")
    void listarTodosTeste() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("teste de listar todos sem parâmetros")
    void testListarTodosTeste() {
        // given
        // when
        // then
    }
}
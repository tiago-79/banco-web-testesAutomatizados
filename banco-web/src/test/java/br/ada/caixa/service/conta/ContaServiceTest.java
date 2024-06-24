package br.ada.caixa.service.conta;

import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.TipoCliente;
import br.ada.caixa.enums.StatusCliente;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.respository.ClienteRepository;
import br.ada.caixa.respository.ContaRepository;
import br.ada.caixa.service.cliente.ClienteService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    private final Random random = new Random();
    private final String cpf = RandomStringUtils.randomAlphanumeric(11);
    private final Long saldo  = random.nextLong();



    @Mock
    ContaRepository contaRepository;
    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteService clienteService;
    @InjectMocks
    ContaService contaService;

    @Test
    @DisplayName("teste abrir poupanca")
    void abrirContaPoupancaTest() {
        // given
        var cliente = Cliente
                .builder()
                .documento("1234567890")
                .nome("Fulano Beltrano")
                .dataNascimento(LocalDate.now())
                .status(StatusCliente.ATIVO)
                .tipo(TipoCliente.PF)
                .createdAt(LocalDate.now())
                .build();
        var conta = Conta.builder().cliente(cliente).build();
        given(clienteRepository.findByDocumento(cliente.getDocumento())).willReturn(Optional.of(cliente));

        // when
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);
        Conta actual = contaService.abrirContaPoupanca(cliente.getDocumento());

        // then
        assertEquals(cliente, conta.getCliente());
//        verify(contaRepository).save(conta);
//        assertNotNull(actual.getSaldo());

    }

    @Test
    @DisplayName("teste exeção de abrir poupanca")
    void abrirContaPoupancaClienteNaoEncontradoTeste() {
        // given
        var cliente = Cliente
                .builder()
                .documento("1234567890")
                .nome("Fulano Beltrano")
                .dataNascimento(LocalDate.now())
                .status(StatusCliente.ATIVO)
                .tipo(TipoCliente.PF)
                .createdAt(LocalDate.now())
                .build();
        var conta = Conta.builder().cliente(cliente).build();

        // when
        Optional<Cliente> actual = clienteRepository.findByDocumento(cliente.getDocumento());

        // then
        assertEquals(actual, new ValidacaoException("Cliente nao encontrado com o CPF informado!"));
//        verify(contaRepository).save(conta);
//        assertNotNull(actual.getSaldo());
    }
}
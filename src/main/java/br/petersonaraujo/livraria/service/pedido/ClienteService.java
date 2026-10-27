package br.petersonaraujo.livraria.service.pedido;

import br.petersonaraujo.livraria.exception.domain.EntidadeNaoEncontradaException;
import br.petersonaraujo.livraria.exception.domain.ErroNegocioException;
import br.petersonaraujo.livraria.model.pedido.ClienteEntity;
import br.petersonaraujo.livraria.repository.pedido.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClienteService {

    public static final String MSG_CLIENTE_JA_CADASTRADO = "Cliente já cadastrado";
    public static final String MSG_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

    private final ClienteRepository clienteRepository;

    public ClienteEntity salvarCliente(ClienteEntity clienteEntity) {
        clienteRepository.findByEmail(clienteEntity.getEmail())
                .ifPresent(cliente -> {
                    throw new ErroNegocioException(MSG_CLIENTE_JA_CADASTRADO);
                });

        clienteEntity.setDataCadastro(LocalDate.now());

        return clienteRepository.save(clienteEntity);
    }

    public ClienteEntity buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_CLIENTE_NAO_ENCONTRADO));
    }

    public ClienteEntity atualizarClientePorId(Long id, ClienteEntity clienteEntity) {
        var clienteEncontrado = buscarClientePorId(id);

        clienteEncontrado.setNome(clienteEntity.getNome());
        clienteEncontrado.setEmail(clienteEntity.getEmail());

        return clienteRepository.save(clienteEncontrado);
    }

    public void removerClientePorId(Long id) {
        var clienteEncontrado = buscarClientePorId(id);

        clienteRepository.delete(clienteEncontrado);
    }

    public ClienteEntity buscarClientePorNome(String nome) {
        return clienteRepository.findByNome(nome)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_CLIENTE_NAO_ENCONTRADO));
    }
}

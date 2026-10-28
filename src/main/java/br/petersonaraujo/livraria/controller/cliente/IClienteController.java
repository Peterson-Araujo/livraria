package br.petersonaraujo.livraria.controller.cliente;

import br.petersonaraujo.livraria.controller.cliente.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Clientes", description = "Operações para gerenciamento de clientes da livraria")
public interface IClienteController {

    @Operation(
            summary = "Cadastrar cliente",
            description = "Cria um novo cliente a partir dos dados informados e retorna o cliente criado."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Cliente já cadastrado", content = @Content)
    })
    ResponseEntity<ClienteDTO> salvarCliente(ClienteDTO dto);

    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna os dados de um cliente a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do cliente",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    ResponseEntity<ClienteDTO> buscarClientePorId(Long id);

    @Operation(
            summary = "Buscar cliente por nome",
            description = "Retorna os dados do cliente correspondente ao nome informado.",
            parameters = @Parameter(name = "nome", description = "Nome do cliente",
                    in = ParameterIn.QUERY, required = true, example = "Maria Silva")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    ResponseEntity<ClienteDTO> buscarClientePorNome(String nome);

    @Operation(
            summary = "Atualizar cliente",
            description = "Atualiza os dados de um cliente existente a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do cliente",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    ResponseEntity<ClienteDTO> atualizarClientePorId(Long id, ClienteDTO dto);

    @Operation(
            summary = "Remover cliente",
            description = "Remove um cliente a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do cliente",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    ResponseEntity<Void> removerClientePorId(Long id);
}
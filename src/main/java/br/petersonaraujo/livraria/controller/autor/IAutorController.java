package br.petersonaraujo.livraria.controller.autor;

import br.petersonaraujo.livraria.controller.autor.dto.AutorCriadoResponse;
import br.petersonaraujo.livraria.controller.autor.dto.AutorRequest;
import br.petersonaraujo.livraria.controller.autor.dto.AutorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Autores", description = "Operações para gerenciamento de autores da livraria")
public interface IAutorController {

    @Operation(
            summary = "Cadastrar autor",
            description = "Cria um novo autor a partir dos dados informados e retorna os dados do autor criado."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Autor criado com sucesso",
                    content = @Content(schema = @Schema(implementation = AutorCriadoResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Autor já cadastrado", content = @Content)
    })
    ResponseEntity<AutorCriadoResponse> salvarAutor(AutorRequest autorRequest);

    @Operation(
            summary = "Buscar autor por ID",
            description = "Retorna os dados de um autor a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do autor",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Autor encontrado",
                    content = @Content(schema = @Schema(implementation = AutorResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = @Content)
    })
    ResponseEntity<AutorResponse> buscarAutorPorId(Long id);

    @Operation(
            summary = "Listar autores por nome",
            description = "Retorna a lista de autores cujo nome corresponde ao valor informado.",
            parameters = @Parameter(name = "nome", description = "Nome (ou parte do nome) do autor",
                    in = ParameterIn.QUERY, required = true, example = "Machado")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de autores retornada com sucesso (pode estar vazia)",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class)))
            )
    })
    ResponseEntity<List<AutorResponse>> listarAutoresPorNome(String nome);

    @Operation(
            summary = "Listar todos os autores",
            description = "Retorna a lista completa de autores cadastrados."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de autores retornada com sucesso (pode estar vazia)",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class)))
            )
    })
    ResponseEntity<List<AutorResponse>> listarAutores();

    @Operation(
            summary = "Atualizar autor",
            description = "Atualiza os dados de um autor existente a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do autor",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Autor atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = AutorResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = @Content)
    })
    ResponseEntity<AutorResponse> atualizarAutor(Long id, AutorRequest autorRequest);

    @Operation(
            summary = "Remover autor",
            description = "Remove um autor a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do autor",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Autor removido com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = @Content)
    })
    ResponseEntity<Void> removerAutor(Long id);
}
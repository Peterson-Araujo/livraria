package br.petersonaraujo.livraria.controller.editora;

import br.petersonaraujo.livraria.controller.editora.dto.EditoraRequest;
import br.petersonaraujo.livraria.controller.editora.dto.EditoraResponse;
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

@Tag(name = "Editoras", description = "Operações para gerenciamento de editoras da livraria")
public interface IEditoraController {

    @Operation(
            summary = "Cadastrar editora",
            description = "Cria uma nova editora a partir dos dados informados e retorna a editora criada."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Editora criada com sucesso",
                    content = @Content(schema = @Schema(implementation = EditoraResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Editora já cadastrada", content = @Content)
    })
    ResponseEntity<EditoraResponse> salvarEditora(EditoraRequest editoraRequest);

    @Operation(
            summary = "Buscar editora por ID",
            description = "Retorna os dados de uma editora a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador da editora",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Editora encontrada",
                    content = @Content(schema = @Schema(implementation = EditoraResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada", content = @Content)
    })
    ResponseEntity<EditoraResponse> buscarEditoraPorId(Long id);

    @Operation(
            summary = "Listar todas as editoras",
            description = "Retorna a lista completa de editoras cadastradas."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de editoras retornada com sucesso (pode estar vazia)",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EditoraResponse.class)))
            )
    })
    ResponseEntity<List<EditoraResponse>> buscarEditoras();

    @Operation(
            summary = "Atualizar editora",
            description = "Atualiza os dados de uma editora existente a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador da editora",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Editora atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = EditoraResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada", content = @Content)
    })
    ResponseEntity<EditoraResponse> atualizarEditoraPorId(Long id, EditoraRequest editoraRequest);

    @Operation(
            summary = "Remover editora",
            description = "Remove uma editora a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador da editora",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Editora removida com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada", content = @Content)
    })
    ResponseEntity<Void> deletarEditoraPorId(Long id);
}
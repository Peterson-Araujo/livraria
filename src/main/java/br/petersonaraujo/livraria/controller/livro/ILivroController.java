package br.petersonaraujo.livraria.controller.livro;

import br.petersonaraujo.livraria.controller.livro.dto.LivroRequest;
import br.petersonaraujo.livraria.controller.livro.dto.LivroResponse;
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

@Tag(name = "Livros", description = "Operações para gerenciamento de livros da livraria")
public interface ILivroController {

    @Operation(
            summary = "Cadastrar livro",
            description = "Cria um novo livro a partir dos dados informados e retorna o livro criado."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Livro criado com sucesso",
                    content = @Content(schema = @Schema(implementation = LivroResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Autor ou editora informados não encontrados", content = @Content),
            @ApiResponse(responseCode = "409", description = "Livro já cadastrado", content = @Content)
    })
    ResponseEntity<LivroResponse> salvarLivro(LivroRequest livroRequest);

    @Operation(
            summary = "Listar todos os livros",
            description = "Retorna a lista completa de livros cadastrados."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de livros retornada com sucesso (pode estar vazia)",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LivroResponse.class)))
            )
    })
    ResponseEntity<List<LivroResponse>> listarLivros();

    @Operation(
            summary = "Buscar livro por ID",
            description = "Retorna os dados de um livro a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do livro",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Livro encontrado",
                    content = @Content(schema = @Schema(implementation = LivroResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    ResponseEntity<LivroResponse> buscarLivroPorId(Long id);

    @Operation(
            summary = "Atualizar livro",
            description = "Atualiza os dados de um livro existente a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do livro",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Livro atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = LivroResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Livro, autor ou editora não encontrados", content = @Content)
    })
    ResponseEntity<LivroResponse> atualizarLivroPorId(Long id, LivroRequest livroRequest);

    @Operation(
            summary = "Remover livro",
            description = "Remove um livro a partir do seu identificador.",
            parameters = @Parameter(name = "id", description = "Identificador do livro",
                    in = ParameterIn.PATH, required = true, example = "1")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Livro removido com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    ResponseEntity<Void> deletarLivroPorId(Long id);
}
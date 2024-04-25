package com.thecommerce.app.domain.user.controller;

import com.thecommerce.app.common.util.Response;
import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import com.thecommerce.app.domain.user.dto.request.UserUpdateRequestDto;
import com.thecommerce.app.domain.user.dto.response.UserListDto;
import com.thecommerce.app.domain.user.dto.response.UserUpdateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원 API", description = "회원 관련 API 입니다.")
public interface UserApiInterface {

    @Operation(summary = "회원가입 API", description = "입력된 정보를 기반으로 회원 정보를 데이터베이스에 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 등록 성공"),
            @ApiResponse(responseCode = "400", description =
                    "1. 누락된 항목이 있을 경우" +
                            "\t\n 2. 비밀번호 형식이 다른 경우 (비밀번호는 4자 이상 20자 이하)" +
                            "\t\n 3. 전화번호 형식이 다른 경우 (전화번호는 10자리 또는 11자리 숫자)" +
                            "\t\n 4. 이메일 형식이 틀린 경우", content = @Content)
    })
    ResponseEntity<Response> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto);

    @Operation(summary = "회원목록 조회 API",
            description = "입력된 회원들의 정보를 목록으로 조회합니다.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "page",
                            description = "페이지 번호 (0부터 시작)",
                            required = true,
                            schema = @Schema(type = "integer", defaultValue = "0")
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "size",
                            description = "한 페이지에 표시될 수 있는 최대 회원 수",
                            allowEmptyValue = true,
                            schema = @Schema(type = "integer", defaultValue = "10")
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "sort",
                            description = "정렬 기준 (예: 가입일순은 'createdAt,asc', 이름순은 'name,asc')",
                            allowEmptyValue = true,
                            schema = @Schema(type = "string", defaultValue = "name,asc")
                    )

            })
    @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공")
    ResponseEntity<Response<UserListDto>> getUsers(
            @Parameter(hidden = true)
            @PageableDefault(size = 10, sort = "name") Pageable pageable);

    @Operation(summary = "회원정보 수정 API", description = "해당하는 회원 고유 식별 id(pk)를 가진 회원의 정보가 수정됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description =
                    "1. 누락된 항목이 있을 경우" +
                            "\t\n 2. 전화번호 형식이 다른 경우 (전화번호는 10자리 또는 11자리 숫자)" +
                            "\t\n 3. 이메일 형식이 틀린 경우", content = @Content)
    })
    ResponseEntity<Response<UserUpdateResponseDto>> updateUser(
            @PathVariable("id") int id,
            @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto);
}

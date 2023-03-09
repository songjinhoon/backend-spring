package com.persoanltoy.backend.domains.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {
    
    SUCCESS_CREATE("A0000", "저장되었습니다."),
    SUCCESS_READ("A0001", "조회되었습니다."),
    SUCCESS_UPDATE("A0003","수정되었습니다."),
    SUCCESS_DELETE("A0004", "삭제되었습니다."),

    ERROR_INTERNAL_SERVER_ERROR("B0000", "서버에 문제가 있습니다. 잠시후 다시 시도해주세요."),
    ERROR_NOT_FOUND("B0001", "값을 찾을 수 없습니다."),

    ACCOUNT_DUPLICATION("C0000", "계정이 중복되었습니다."),
    ACCOUNT_AVAILABLE("C0001", "계정 사용이 가능합니다."),
    ERROR_ACCOUNT_LOGIN("C0002", "아이디/비밀버번호를 확인해주세요."),
    ERROR_AUTHENTICATION("C0003", "인증이 필요합니다."),
    ERROR_FORBIDDEN("C0004", "권한이 부족합니다."),

    NOT_VALID_REQUEST_DATA_EXCEPTION("E00000", "요청 데이터가 유효하지 않습니다.");

    private final String code;

    private final String value;

}

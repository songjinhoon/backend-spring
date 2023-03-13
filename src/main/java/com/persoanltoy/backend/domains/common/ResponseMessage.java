package com.persoanltoy.backend.domains.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {

    SUCCESS_CREATE("A0000", "저장되었습니다."),
    SUCCESS_READ("A0001", "조회되었습니다."),
    SUCCESS_UPDATE("A0003", "수정되었습니다."),
    SUCCESS_DELETE("A0004", "삭제되었습니다."),
    ERROR_AUTHENTICATION("C0003", "인증이 필요합니다."),
    ERROR_FORBIDDEN("C0004", "권한이 부족합니다."),
    EXTERNAL_API_CONNECT_ERROR("A0000", "외부 API 연계 에러 발생"),
    VERSION_CONFLICT_ERROR("A0001", "버전 충돌 발생");

    private final String code;

    private final String value;

}

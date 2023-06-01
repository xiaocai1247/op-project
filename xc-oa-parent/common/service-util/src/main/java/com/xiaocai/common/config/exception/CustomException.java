package com.xiaocai.common.config.exception;

import com.xiaocai.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private Integer code;//状态码
    private String msg;//描述信息

    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum
     */
    public CustomException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "code=" + code +
                ", msg='" + this.getMessage() + '\'' +
                '}';
    }
}

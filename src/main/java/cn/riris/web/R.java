package cn.riris.web;

import cn.riris.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author bryce
 * 2019/7/1 14:24
 */
@ToString
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class R<T> {
    /**
     * 通配符
     */
    public static final String WILDCARD_ALL = "*";
    @ApiModelProperty(value = "响应状态码", position = 1)
    private volatile String respCode = RespCode.BAD_REQUEST.respCode;
    @ApiModelProperty(value = "响应提示信息", position = 2)
    private volatile String respMsg = RespCode.BAD_REQUEST.respMsg;
    @ApiModelProperty(value = "响应内容", position = 3)
    private volatile T respData;
    @ApiModelProperty(value = "图片服务器前缀", position = 4)

    private volatile String prefix;
    @ApiModelProperty(value = "服务器时间戳", position = 5)

    private volatile Long timestamp;
    /**
     * 需要过滤属性
     */
    @JsonIgnore
    private volatile String filterFields = WILDCARD_ALL;
    /**
     * 自定义导出标题
     */
    @JsonIgnore
    private volatile LinkedHashMap<String, String> exportTitleMap;

    public R(final String respCode) {
        this(respCode, null, null);
    }

    public R(final String respCode, final String respMsg) {
        this(respCode, respMsg, null);
    }

    public R(final String respCode, final String respMsg, final T respData) {
        this.respCode = respCode;
        this.respData = respData;
        this.respMsg = respMsg;
    }

    public R(final String respCode, final String respMsg, final T respData, String prefix) {
        this.respCode = respCode;
        this.respData = respData;
        this.respMsg = respMsg;
        this.prefix = prefix;
    }

    public static <T> R<T> empty() {
        return new R<>();
    }

    public static <T> R<T> ok(final RespCode ok, final String message) {

        return new R<>(ok.getRespCode(), message);
    }

    public static <T> R<T> badRequest(final RespCode fail, final String message) {

        return new R<>(fail.getRespCode(), message);
    }

    public static <T> R<T> ok() {

        return new R<>(RespCode.OK.getRespCode(), RespCode.OK.getRespMsg());
    }

    public static <T> R<T> ok(final String message) {

        return new R<>(RespCode.OK.getRespCode(), message);
    }

    public static <T> R<T> ok(final String ok, final String message) {

        return new R<>(ok, message);
    }

    public static <T> R<T> okPrefix(final T respData, final String prefix) {

        return new R<>(RespCode.OK.getRespCode(), RespCode.OK.getRespMsg(), respData, prefix);

    }

    public static <T> R<T> success(final T respData) {

        return new R<>(RespCode.OK.getRespCode(), RespCode.OK.getRespMsg(), respData);

    }

    public static <T> R<T> error() {

        return new R<>(RespCode.ERROR.getRespCode(), RespCode.ERROR.getRespMsg());

    }

    public static <T> R<T> error(final String respMsg) {

        return new R<>(RespCode.ERROR.getRespCode(), respMsg);

    }

    public static <T> R<T> error(final String error, final String respMsg) {

        return new R<>(error, respMsg);

    }


    public static <T> R<T> badRequest() {

        return new R<>(RespCode.BAD_REQUEST.getRespCode(), RespCode.BAD_REQUEST.getRespMsg());

    }

    public static <T> R<T> badRequest(final String respMsg) {

        return new R<>(RespCode.BAD_REQUEST.getRespCode(), respMsg);

    }

    public static <T> R<T> badRequest(final String error, final String respMsg) {

        return new R<>(error, respMsg);

    }

    public static <T> R<T> forbidden() {

        return new R<>(RespCode.FORBIDDEN.getRespCode(), RespCode.FORBIDDEN.getRespMsg());

    }

    public static <T> R<T> forbidden(final String respMsg) {

        return new R<>(RespCode.FORBIDDEN.getRespCode(), respMsg);

    }

    public static R unauthorized(String s) {
        return new R<>(RespCode.UNAUTHORIZED.getRespCode(), s);

    }


    public static R internalServerError() {
        return new R<>(RespCode.INTERNAL_SERVER_ERROR.getRespCode(), RespCode.INTERNAL_SERVER_ERROR.getRespMsg());

    }

    public static R internalServerError(String msg) {
        return new R<>(RespCode.INTERNAL_SERVER_ERROR.getRespCode(), msg);

    }

    public R add(final String key, final Object value) {
        if (Objects.isNull(this.respData)) {
            this.respData = (T) new HashMap<String, Object>(0);
            Map<String, Object> content = (Map<String, Object>) this.respData;
            content.put(key, value);
            return this;
        }
        if (!(this.respData instanceof Map)) {
            return this;
        }
        ((Map) this.respData).put(key, value);
        return this;
    }

    /**
     * 过滤respData中的字段
     *
     * @param filterFields 需要过滤的字段
     * @return 过滤后的实体
     */
    public R setFilterFields(final String filterFields) {
        if (Objects.isNull(filterFields) || WILDCARD_ALL.equals(filterFields)) {
            return this;
        }
        this.filterFields = WILDCARD_ALL + ",respData[" + filterFields.trim() + "]";
        return this;
    }

    public R setFilterFieldsForPaging(final String filterFields) {
        if (Objects.isNull(filterFields) || WILDCARD_ALL.equals(filterFields)) {
            return this;
        }
        this.filterFields = WILDCARD_ALL + ",respData[*,list[" + filterFields.trim() + "]]";
        return this;
    }

    public R setFilterFieldsAndFlush(final String filterFields) {
        return this.setFilterFieldsAndFlush(filterFields, false);
    }

    public R setFilterFieldsAndFlush(final String filterFields, boolean isFilterPaging) {
        if (isFilterPaging) {
            return this.setFilterFieldsForPaging(filterFields)
                       .filterFieldsAndFlush();
        }
        return this.setFilterFields(filterFields)
                   .filterFieldsAndFlush();
    }

    public R filterFieldsAndFlush() {
        return JsonUtils.jsonToType(this.toJson(), this.getClass());
    }

    public String toJson() {
        if (this.isNotFieldsFilter()) {
            return JsonUtils.toJson(this);
        }
        return JsonUtils.toFilterJson(this, this.getFilterFields());
    }

    @JsonIgnore
    public boolean isNotFieldsFilter() {
        return Objects.isNull(this.getFilterFields()) || WILDCARD_ALL.equals(this.getFilterFields());
    }

    @JsonIgnore
    public boolean isFieldsFilter() {
        return !isNotFieldsFilter();
    }

    @JsonIgnore
    public boolean isOk() {
        return RespCode.OK.getRespCode()
                          .equals(this.getRespCode());
    }

    @JsonIgnore
    public boolean isNotOk() {
        return !isOk();
    }


    @Getter
    public enum RespCode {
        OK("10000", "请求成功"), ERROR("99999", "请求失败"), SQL_EXCEPTION("90001", "资源过期"), BAD_REQUEST("10400", "请求失败"), UNAUTHORIZED("10401", "身份验证失败"), FORBIDDEN("10403", "权限不足"), METHOD_NOT_ALLOWED("10405", "目标资源不支持该请求方式"), NOT_ACCEPTABLE("10404", "请求格式错误"), REQUEST_TIME_OUT("10408", "服务器等待客户端发送请求时间过长,超时"), TOO_MANY_REQUESTS("10429", "太多的请求"), INTERNAL_SERVER_ERROR("10500", "请求出错");


        private final String respCode;
        private final String respMsg;

        RespCode(String respCode, String respMsg) {
            this.respCode = respCode;
            this.respMsg = respMsg;
        }

    }
}

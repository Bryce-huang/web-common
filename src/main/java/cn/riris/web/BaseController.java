package cn.riris.web;

import cn.riris.exception.ValidatedIllegalArgumentException;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import java.util.regex.Pattern;

/**
 * @author riris
 * 2019/7/4 16:51
 *  支持嵌套异常检查
 */
public class BaseController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String DOT = ".";
    private static final String NESTED_REGEX = ("\\[\\d+]\\.");

    private static final Pattern INSTEAD = Pattern.compile(NESTED_REGEX);


    protected void validIgnore(BindingResult bindingResult, String... ignoreFields) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                         .forEach(result ->
                         {
                             if (!Sets.newHashSet(ignoreFields)
                                      .contains(INSTEAD.matcher(result.getField())
                                                       .replaceAll(DOT))) {
                                 throw new ValidatedIllegalArgumentException(result.getDefaultMessage(), bindingResult);
                             }
                         });

        }
    }

    protected void validRequire(BindingResult bindingResult, String... requireFields) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                         .forEach(result ->
                         {
                             if (Sets.newHashSet(requireFields)
                                     .contains(INSTEAD.matcher(result.getField())
                                                      .replaceAll(DOT))) {
                                 throw new ValidatedIllegalArgumentException(result.getDefaultMessage(), bindingResult);
                             }
                         });

        }
    }

}

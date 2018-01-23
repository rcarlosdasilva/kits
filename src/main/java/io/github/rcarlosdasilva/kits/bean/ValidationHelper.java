package io.github.rcarlosdasilva.kits.bean;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 验证Bean工具
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class ValidationHelper {

  private static final Logger logger = LoggerFactory.getLogger(ValidationHelper.class);

  private static final Validator validator;

  /**
   * 初始验证器
   */
  static {
    ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure()
        .buildValidatorFactory();
    validator = factory.getValidator();
  }

  /**
   * 验证是否通过.
   *
   * @param <T>    目标类型
   * @param target 被验证对象
   * @return 是否通过
   */
  public static <T> boolean isValidated(T target) {
    Set<ConstraintViolation<T>> violations = validator.validate(target);
    if (violations.size() > 0) {
      return false;
    }
    return true;
  }

  /**
   * 验证，如验证不通过，产生异常.
   *
   * @param <T>    目标类型
   * @param target 被验证对象
   */
  public static <T> void validate(T target) {
    Set<ConstraintViolation<T>> violations = validator.validate(target);
    if (violations.size() > 0) {
      throw new RuntimeException(violations.iterator().next().getMessage());
    }
  }

  /**
   * 使用给定的正则表达式匹配.
   *
   * @param str   被匹配字符串
   * @param regex 正则表达式
   * @return 是否匹配上
   */
  public static boolean match(String str, String regex) {
    if (str != null && str.matches(regex)) {
      return true;
    }
    logger
        .warn("[[ Can't match a legal string, maybe this is important value you needed ]] " + str);
    return false;
  }

}

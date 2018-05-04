package cn.rain.exception;

/**
 * description:
 *
 * @author 任伟
 * @date 2018/5/4 0:14
 */
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}

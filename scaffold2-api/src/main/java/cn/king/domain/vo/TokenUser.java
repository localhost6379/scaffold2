package cn.king.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 登录用户
 */
@Data
@AllArgsConstructor
public class TokenUser {

    private String username;

    private List<String> permissions;

}

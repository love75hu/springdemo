package cn.mediinfo.springdemo.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * spring security自定义配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigtion {

    /**
     * 自定义访问资源规则配置
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * 表单
         */
        http.authorizeRequests().requestMatchers("/login","/logout","/index","/swagger-ui/index.html").permitAll() //匿名登录资源需要放在所有的规则前面
                .requestMatchers("*").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//用来指定默认的登录页，注意：一旦自定义登录页面，必须指定loginProcessingUrl且该路径和from表单的action一致。
                .loginProcessingUrl("/userlogin") //from表单的action地址
                .usernameParameter("username") //from表单的username参数
                .passwordParameter("password")//from表单的password参数
                //.successForwardUrl("/mediinfo-springdemo/swagger-ui/index.html")//登录成功的页面，无论是否指定trturnurl都进入该页面，不可和defaultSuccessUrl同时使用
                .defaultSuccessUrl("/swagger-ui/index.html")//默认登录成功页面，如果登录指定了returnurl则不会使用该页面，不可和successForwardUrl同时使用
                .failureForwardUrl("/error")//登录失败的页面
                //.successHandler(new MyAuthenticationSuccessHandler())  //登录成功后自定义handelr
                .and()
                .logout()
                //.logoutUrl("/logout")//自定义登出页面
                //.logoutSuccessHandler()
                //.logoutSuccessUrl("")//登出后跳转地址
                //.and()
                //.httpBasic() //开启httpBasic认证
                .and()
                //.oauth2Login()
                //.and()
                .csrf().disable()//关闭csrf防伪伪造击
                 ;


        return http.build();

    }

    /**
     * 设置数据源为 jdbc
     * @return
     */
//    @Bean
//    DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(H2)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }

    /**
     * 设置加密方式
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
////        // 将密码加密方式采用委托方式，默认以BCryptPasswordEncoder方式进行加密，但兼容ldap,MD4,MD5等方式
////        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//       return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        // 使用明文方式 不建议
//       // return NoOpPasswordEncoder.getInstance();
//    }

    /**
     * 用户信息通过jdbc数据源
     * @param dataSource
     * @return
     */
//    @Bean
//    UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$fblFvnKOoMhxibNuf1kmQ.GfZxvNcD7MUWKRiq7/wHrDZDUmb8VZW")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER", "ADMIN")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(admin);
//        return users;
//    }



}

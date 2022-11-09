package core.configuration;

import core.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

   @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       httpSecurity
               .authorizeRequests()
               .antMatchers("/sign-up").not().fullyAuthenticated()
               //Доступ только для пользователей с ролью Администратор
               .antMatchers("/admin/**").hasRole("ADMIN")
               .antMatchers("/**").hasRole("USER")
               //Доступ разрешен всем пользователей
               .antMatchers("/", "/resources/**").permitAll()
               //Все остальные страницы требуют аутентификации
               .anyRequest().authenticated()
               .and()
                    //Настройка для входа в систему
                    .formLogin()
                    .loginPage("/login")
                    //Перенаправление на главную страницу после успешного входа
                    .defaultSuccessUrl("/")
                    .permitAll()
               .and()
                    .logout()
                    // логаут разрешаем всем
                    .permitAll()
                    // после логаута отправляем на стартовую страницу
                    .logoutSuccessUrl("/")
               //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
               .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/", "/sign-up", "/login");
    }
}

package cn.lkhcoding.springboot.config;

import cn.lkhcoding.springboot.component.LoginHandlerInterceptor;
import cn.lkhcoding.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                /**
                 * springboot做好了对静态资源的映射，不需要排除静态资源的拦截
                 */
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**").excludePathPatterns
                        ("/","/index.html","/user/login","/static/**","/webjars/**");
            }


        };
        return webMvcConfigurer;
    }


    /**
     * 添加区域信息
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}

package com.xma.surveys;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/surveys").setViewName("surveys");
        registry.addViewController("/questions").setViewName("questions");
        registry.addViewController("/answers").setViewName("answers");
        registry.addViewController("/questionnaire").setViewName("questionnaire");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

    }
//    @Bean
//    public ClassLoaderTemplateResolver secondTemplateResolver() {
//        return newTemplateResolver("surveys/");
//    }
//
//    public ClassLoaderTemplateResolver newTemplateResolver(String path) {
//        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
//        resolver.setPrefix(path);
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode(TemplateMode.HTML);
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setCheckExistence(true);
//
//        return resolver;
//    }
}

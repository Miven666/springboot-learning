# validation
- controller 层校验方式
- service 层校验方式
- component 组件校验方式
- groups 分组校验

## 源码分析
- org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration.defaultValidator
- org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.EnableWebMvcConfiguration
    + org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration.getValidator
        + org.springframework.web.servlet.config.annotation.WebMvcConfigurerComposite.getValidator
            + org.springframework.boot.autoconfigure.validation.ValidatorAdapter.get
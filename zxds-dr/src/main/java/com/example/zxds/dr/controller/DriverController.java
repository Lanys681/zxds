package com.example.zxds.dr.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.zxds.common.util.R;
import com.example.zxds.dr.controller.form.RegisterNewDriverForm;
import com.example.zxds.dr.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverController {
    private final DriverService driverService;

    public R registerNewDriver(@RequestBody @Valid RegisterNewDriverForm form) {
        Map<String, Object> params = BeanUtil.beanToMap(form);
        long driverId = driverService.registerNewDriver(params);
        return R.ok().put("result", driverId);
    }
}

package com.github.zuihou.sms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.sms.dto.SmsSendStatusSaveDTO;
import com.github.zuihou.sms.dto.SmsSendStatusUpdateDTO;
import com.github.zuihou.sms.entity.SmsSendStatus;
import com.github.zuihou.sms.service.SmsSendStatusService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsSendStatus")
@Api(value = "SmsSendStatus", tags = "短信发送状态")
public class SmsSendStatusController extends BaseController {

    @Autowired
    private SmsSendStatusService smsSendStatusService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询短信发送状态
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询短信发送状态", notes = "分页查询短信发送状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询短信发送状态")
    public R<IPage<SmsSendStatus>> page(SmsSendStatus data) {
        IPage<SmsSendStatus> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<SmsSendStatus> query = Wraps.lbQ(data);
        smsSendStatusService.page(page, query);
        return success(page);
    }

    /**
     * 查询短信发送状态
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询短信发送状态", notes = "查询短信发送状态")
    @GetMapping("/{id}")
    @SysLog("查询短信发送状态")
    public R<SmsSendStatus> get(@PathVariable Long id) {
        return success(smsSendStatusService.getById(id));
    }

    /**
     * 新增短信发送状态
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增短信发送状态", notes = "新增短信发送状态不为空的字段")
    @PostMapping
    @SysLog("新增短信发送状态")
    public R<SmsSendStatus> save(@RequestBody @Validated SmsSendStatusSaveDTO data) {
        SmsSendStatus smsSendStatus = dozer.map(data, SmsSendStatus.class);
        smsSendStatusService.save(smsSendStatus);
        return success(smsSendStatus);
    }

    /**
     * 修改短信发送状态
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改短信发送状态", notes = "修改短信发送状态不为空的字段")
    @PutMapping
    @SysLog("修改短信发送状态")
    public R<SmsSendStatus> update(@RequestBody @Validated(SuperEntity.Update.class) SmsSendStatusUpdateDTO data) {
        SmsSendStatus smsSendStatus = dozer.map(data, SmsSendStatus.class);
        smsSendStatusService.updateById(smsSendStatus);
        return success(smsSendStatus);
    }

    /**
     * 删除短信发送状态
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除短信发送状态", notes = "根据id物理删除短信发送状态")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除短信发送状态")
    public R<Boolean> delete(@PathVariable Long id) {
        smsSendStatusService.removeById(id);
        return success(true);
    }

}

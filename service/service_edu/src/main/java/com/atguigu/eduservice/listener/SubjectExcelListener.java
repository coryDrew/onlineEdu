package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.sql.Wrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //该类不能交给spring管理，需要自己new需要的对象，不能自动注入
    //得创建一个属性由外部引入service对象
    public EduSubjectService subjectService;
    public SubjectExcelListener(){

    }
    public SubjectExcelListener(EduSubjectService subjectService){
        this.subjectService = subjectService;
    }
    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if(data == null){
            throw new GuliException(20001,"文件数据为空");

        }
        //一行一行读取
        EduSubject existOneSubject = this.existOneSubject(subjectService, data.getOneSubjectName());
        if(existOneSubject == null){//没有相同的一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(data.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        //取一级分类的id值，真麻烦
        String pid = existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, data.getTwoSubjectName(),pid);
        if(existTwoSubject == null){//没有相同的一级分类，进行添加
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(data.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }
    public EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
    public EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}

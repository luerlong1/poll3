package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.service.ISurveyService;
import com.briup.apps.poll.util.MsgResponse;
import com.briup.apps.poll.vm.SurveyAndAnswersVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 
 * @author luerlong
 *
 */
@Api(description = "答题卡相关接口")
@RestController
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	private ISurveyService surveySurvey;
	@Autowired
	private IAnswersService answersService;

	@GetMapping("findAllSurveyVM")
	@ApiOperation(value = "查找所有课调")
	public MsgResponse findAllSurveyVM() {
		try {
			List<SurveyVM> list = surveySurvey.selectAll();
			// 返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {

			e.printStackTrace();
			// 返回失败
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "通过班级id查找课调", notes = "返回课调的信息以及课调下所有答卷的信息")
	@GetMapping("findSurveyByClazzId")
	public MsgResponse findSurveyByClazzId(long id) {
		try {
			List<SurveyVM> list = surveySurvey.findByClazzIdAndCheckPass(id);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			// 返回失败
			return MsgResponse.error(e.getMessage());
		}
		
	}
		
	
	@ApiOperation(value = "去审核课调", notes = "返回课调的信息以及课调下所有答卷的信息")
	@GetMapping("toCheckSurvey")
	public MsgResponse toCheckSurvey(long id) {
		try {
			// 查询课调信息
			SurveyVM surveyVM = surveySurvey.findByIdVM(id);
			// 如果课调状态未审核才能审核
			if (surveyVM.getStatus().equals(Survey.STATUS_CHECK_UN)) {
				// 查询出该课调下所有答卷
				List<Answers> list = answersService.findAnswersBySurveyId(id);
				// 计算出课调的平均分
				// 所有单个平均分的总和
				double total = 0;
				for (Answers answers : list) {
					String[] arr = answers.getSelections().split("[|]");
					double singleTotal = 0;
					for (String a : arr) {
						singleTotal += Integer.parseInt(a);
					}
					// 每个学生对老师的平均分
					double singAverage = singleTotal / arr.length;
					total += singAverage;
				}
				double average = total / list.size();
				surveyVM.setAverage(average);
				/**
				 * //将平均分保存在数据库
				 */
				Survey survey = surveySurvey.findSurveyById(id);

				survey.setAverage(average);
				surveySurvey.saveOrUpdate(survey);

				// surveyvm 和list返回
				SurveyAndAnswersVM savm = new SurveyAndAnswersVM();
				savm.setAnswers(list);
				savm.setSurveyVM(surveyVM);
				return MsgResponse.success("success", savm);
			} else {
				return MsgResponse.error("课调状态不合法");
			}
		} catch (Exception e) {
			return MsgResponse.error(e.getMessage());
		}

	}

	@ApiOperation(value = "关闭课调", notes = "只有课调状态为开启状态下才能关闭")
	@GetMapping("stopSurvey")
	public MsgResponse stopSurvey(long id) {
		try {
			// 通过id查询课调
			Survey survey = surveySurvey.findSurveyById(id);
			// 修改课调状态、课调编号
			if (survey != null && survey.getStatus().equals(Survey.STATUS_BEGIN)) {

				survey.setStatus(Survey.STATUS_CHECK_UN);

				surveySurvey.saveOrUpdate(survey);
				return MsgResponse.success("关闭课调成功", null);
			} else {
				return MsgResponse.error("状态不合法");

			}
		} catch (Exception e) {
			return MsgResponse.error("失败关闭" + e.getMessage());
		}
	}

	@ApiOperation(value = "预览课调", notes = "只有课调为通过状态下才能预览")
	@GetMapping("previewSurvey")
	public MsgResponse previewSurvey(long id) {
		try {
			// 课调的信息（课程 班级 讲师 问卷 平均分）
			SurveyVM surveyVM = surveySurvey.findByIdVM(id);
			if (surveyVM != null && surveyVM.getStatus().equals(Survey.STATUS_CHECK_PASS)) {
				// 课调的结果 主观题列表 answers
				List<Answers> answers = answersService.findAnswersBySurveyId(id);
				// 将课调信息和课调答卷封装到一个对象中
				SurveyAndAnswersVM savm = new SurveyAndAnswersVM();
				savm.setSurveyVM(surveyVM);
				savm.setAnswers(answers);
				return MsgResponse.success("success", savm);
			} else {
				return MsgResponse.error("课调状态不合法");
			}
		} catch (Exception e) {
			return MsgResponse.error(e.getMessage());
		}

	}

	@ApiOperation(value = "核审课调", notes = "只有课调为未审核状态下才能被审核，" + "参数id表示课调编号，参数status的取值只能为0/1,"
			+ "如果是0，表示不通过，如果是一表示审核通过")
	@GetMapping("checkSurvey")
	public MsgResponse checkSurvey(long id, int status) {
		try {
			// 通过id查找课调
			Survey survey = surveySurvey.findSurveyById(id);
			// 判断当前课调是否为未审核状态
			if (survey != null && survey.getStatus().equals(Survey.STATUS_CHECK_UN)) {
				if (status == 0) {
					// 核审不通过
					survey.setStatus(Survey.STATUS_CHECK_NOPASS);
				} else {
					// 核审通过
					survey.setStatus(Survey.STATUS_CHECK_PASS);
				}
				surveySurvey.saveOrUpdate(survey);
				return MsgResponse.success("核审完成", null);
			} else {
				return MsgResponse.error("状态不合法");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "开启课调", notes = "只有课调状态为未开启状态下才能开启")
	@GetMapping("beginSurvey")
	public MsgResponse beginSurvey(long id) {
		try {
			// 通过id查询课调
			Survey survey = surveySurvey.findSurveyById(id);
			// 修改课调状态、课调编号
			if (survey.getStatus().equals(Survey.STATUS_INIT)) {
				// 将课调设置为开启
				survey.setStatus(Survey.STATUS_BEGIN);
				survey.setCode(survey.getId().toString());
				// 执行更新操作
				surveySurvey.saveOrUpdate(survey);
				return MsgResponse.success("开启成功", null);
			} else {
				return MsgResponse.error("状态不合法");
			}
		} catch (Exception e) {
			//

			return MsgResponse.error("开启失败" + e.getMessage());

		}

	}

	@ApiOperation(value = "根据id查找课调")
	@GetMapping("findByIdVM")
	public MsgResponse findByIdVM(@RequestParam long id) {
		//
		SurveyVM survey = new SurveyVM();
		try {
			survey = surveySurvey.findByIdVM(id);
			return MsgResponse.success("success", survey);
		} catch (Exception e) {
			//
			e.printStackTrace();
			return MsgResponse.error("查找失败" + e.getMessage());
		}

	}

	@PostMapping("saveOrUpdate")
	@ApiOperation(value = "添加或更新课调", notes = "添加不需要输入id,只需输入CourseID,ClazzId,UserId,QUestionnaireId")

	public MsgResponse saveOrUpdate(Survey survey) {
		//
		try {
			surveySurvey.saveOrUpdate(survey);
			return MsgResponse.success("保存或更新成功", null);
		} catch (Exception e) {
			//
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@GetMapping("query")
	@ApiOperation(value = "根据surveydate查找课调")

	public List<Survey> query(String keywords) {

		try {
			return surveySurvey.query(keywords);
		} catch (Exception e) {
			//
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("deleteById")
	@ApiOperation(value = "根据id删除课调")

	public MsgResponse deleteById(@RequestParam long id) {
		//
		try {
			surveySurvey.deleteById(id);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			//
			e.printStackTrace();
			return MsgResponse.error("删除失败" + e.getMessage());
		}
	}

	@GetMapping("batchDelete")
	@ApiOperation(value = "批量删除课调")

	public MsgResponse batchDelete(@RequestParam List<Long> ids) {
		//

		try {
			surveySurvey.batchDelete(ids);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error("删除失败" + e.getMessage());

		}
	}
}

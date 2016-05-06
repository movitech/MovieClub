/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.api.mobile;


import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.movieclub.comment.entity.MovieConsumerComments;
import com.thinkgem.jeesite.movieclub.comment.service.MovieConsumerCommentsService;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;
import com.thinkgem.jeesite.movieclub.consumer.service.ConsumerService;
import com.thinkgem.jeesite.movieclub.util.Const;
import com.thinkgem.jeesite.movieclub.util.Message;
import com.thinkgem.jeesite.movieclub.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * commentController
 * @author eric.wang
 * @version 2015-10-16
 */
@Controller
@RequestMapping(value = "${mobileApiPath}/comments")
public class CommentsAPIController
		extends BaseController {

	@Autowired
	private MovieConsumerCommentsService movieConsumerCommentsService;

	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addToBucket(@RequestBody CommentVO commentVO,@RequestParam(required=false) String token) {
		Consumer consumer = consumerService.getByToken(token,"mt");
		if (null != consumer) {
			MovieConsumerComments comment = new MovieConsumerComments();
			BeanUtils.copyProperties(commentVO, comment);
			movieConsumerCommentsService.save(comment);
			return Const.APIResult.SUCCESS_MESSAGE;
		}else {
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_EXIST_OR_TOKEN_EXPIRED, Message.Consumer.ACCOUNT_DOES_NOT_EXISTS_OR_TOKEN_EXPIRED);
		}
	}

	@RequestMapping(value = "/getMovieComments", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentVO> getMyBucket(@RequestParam(required=true) String id){
		List<CommentVO> list=new ArrayList<CommentVO>();

		List<MovieConsumerComments> movieComments= movieConsumerCommentsService.getMovieComments(id);
		for(MovieConsumerComments item : movieComments){
			CommentVO commentVO=new CommentVO();
			BeanUtils.copyProperties(item, commentVO);
			list.add(commentVO);
		}
		return list;
	}

}
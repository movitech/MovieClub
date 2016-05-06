/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.api.mobile;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.movieclub.bucket.entity.MovieBucket;
import com.thinkgem.jeesite.movieclub.bucket.service.MovieBucketService;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;
import com.thinkgem.jeesite.movieclub.consumer.service.ConsumerService;
import com.thinkgem.jeesite.movieclub.util.Const;
import com.thinkgem.jeesite.movieclub.util.Message;
import com.thinkgem.jeesite.movieclub.vo.BucketVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * BucketController
 * @author eric.wang
 * @version 2015-10-16
 */
@Controller
@RequestMapping(value = "${mobileApiPath}/bucket")
public class BucketAPIController
		extends BaseController {

	@Autowired
	private MovieBucketService movieBucketService;

	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value = "/addToBucket", method = RequestMethod.POST)
	@ResponseBody
	public String addToBucket(@RequestBody BucketVO bucketVO,@RequestParam(required=false) String token) {
		Consumer consumer = consumerService.getByToken(token,"mt");
		if (null != consumer) {
			MovieBucket movieBucket=movieBucketService.getByMovieIdAndUserId(bucketVO.getMovieId(),bucketVO.getConsumerId());
			if(movieBucket==null){
				movieBucket=new MovieBucket();
			}
			BeanUtils.copyProperties(bucketVO, movieBucket);
			movieBucketService.save(movieBucket);
			return Const.APIResult.SUCCESS_MESSAGE;
		}else {
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_EXIST_OR_TOKEN_EXPIRED, Message.Consumer.ACCOUNT_DOES_NOT_EXISTS_OR_TOKEN_EXPIRED);
		}
	}

	@RequestMapping(value = "/removeFromBucket", method = RequestMethod.POST)
	@ResponseBody
	public String removeFromBucket(@RequestBody BucketVO bucketVO,@RequestParam(required=false) String token) {
		Consumer consumer = consumerService.getByToken(token,"mt");
		if (null != consumer) {
			MovieBucket movieBucket=movieBucketService.getByMovieIdAndUserId(bucketVO.getMovieId(),bucketVO.getConsumerId());
			if(movieBucket!=null){
				movieBucketService.delete(movieBucket);
			}
			return Const.APIResult.SUCCESS_MESSAGE;
		}else {
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_EXIST_OR_TOKEN_EXPIRED, Message.Consumer.ACCOUNT_DOES_NOT_EXISTS_OR_TOKEN_EXPIRED);
		}
	}

	@RequestMapping(value = "/getMyBucket", method = RequestMethod.GET)
	@ResponseBody
	public List<BucketVO> getMyBucket(@RequestParam(required=false) String token){
		List<BucketVO> list=new ArrayList<BucketVO>();

		Consumer consumer = consumerService.getByToken(token,"mt");
		if (null != consumer) {
			List<MovieBucket> movieBuckets=movieBucketService.getMyBucket(consumer.getId());
			for(MovieBucket item : movieBuckets){
				BucketVO bucketVO = new BucketVO();
				BeanUtils.copyProperties(item, bucketVO);
				//todo fake data
				switch (Integer.valueOf(bucketVO.getMovieId()).intValue()){
					case 3:
						bucketVO.setTitle("Byzantium");
						bucketVO.setMovieImage("movie-image-206-Byzantium.jpg");
						break;
					case 4:
						bucketVO.setTitle("Rush");
						bucketVO.setMovieImage("movie-image-207-rush.jpg");
						break;
					case 5:
						bucketVO.setTitle("3 Days to Kill");
						bucketVO.setMovieImage("movie-image-211-3DAYSTOKILL.jpg");
						break;
					case 6:
						bucketVO.setTitle("Earth to Echo");
						bucketVO.setMovieImage("movie-image-227-EarthToEcho.jpg");
						break;
				}
				list.add(bucketVO);
			}
		}
		return list;
	}
}
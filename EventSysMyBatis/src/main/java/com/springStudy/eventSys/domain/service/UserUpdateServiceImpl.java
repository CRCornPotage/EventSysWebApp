package com.springStudy.eventSys.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springStudy.eventSys.common.exception.BusinessException;
import com.springStudy.eventSys.domain.entity.User;
import com.springStudy.eventSys.domain.mapper.UserMapper;

/**
 * ユーザ情報を更新するサービスクラス
 */
@Service
@Transactional
public class UserUpdateServiceImpl implements UserUpdateService {
	
	/** ユーザ情報を操作するMapperオブジェクト */
	private final UserMapper userMapper;
	
	/** コンストラクタインジェクション */
	public UserUpdateServiceImpl(UserMapper userMapper) {
		
		this.userMapper = userMapper;
		
	}
	
	/**
	 * ユーザIDが存在するか確認するプライベートメソッド
	 * @param user
	 * @throws BusinessException
	 */
	private void confirmUserId(User user) throws BusinessException {
		
		/** ユーザ情報の存在チェック */
		// ユーザIDを引数にしてユーザ情報を取得出来なかった場合に実行
		if(userMapper.getUserById(user.getId()) == null) {
			
			// エラーメッセージを設定
			String message = "ユーザーIDが存在しません";
			
			// 業務エラーを投げる
			throw new BusinessException(message);
			
		}
		
	}
	
	/**
	 * ユーザ情報が適切か確認するメソッド
	 * @param user
	 * @throws BusinessException
	 */
	@Override
	@Transactional
	public void confirmUser(User user) throws BusinessException {
		
		// エラーメッセージを格納する変数
		String message = "";
		
		try {
			
			// ユーザIDの存在チェック
			confirmUser(user);
			
		}catch(BusinessException e) {
			
			// 業務エラーを投げる
			throw e;
			
		}
		
		/** ユーザ名の重複チェック */
		// ユーザ名を引数にしてユーザ情報を取得出来た場合に実行
		if(userMapper.getUserByUsername(user.getUsername()) != null) {
			
			// エラーメッセージを設定
			message = "ユーザー名が重複しています";
			
			// 業務エラーを投げる
			throw new BusinessException(message);
			
		}
		
		/* メールアドレスの重複チェック */
		// メールアドレスを引数にしてユーザ情報を取得出来た場合に実行
		if(userMapper.getUserByEmail(user.getEmail()) != null) {
			
			// エラーメッセージを設定
			message = "メールアドレスが重複しています";
			
			// 業務エラーを投げる
			throw new BusinessException(message);
			
		}
		
	}
	
	/**
	 * ユーザ情報を登録するメソッド
	 * @param user
	 * @throws BusinessException
	 */
	@Override
	@Transactional
	public void updateUser(User user) throws BusinessException {
		
		try {
			
			// ユーザ情報が適切か確認
			confirmUser(user);
			
		}catch(BusinessException e) {
			
			// 業務エラーを投げる
			throw e;
			
		}
		
		// ユーザ情報を登録
		userMapper.updateUser(user);
		
	}
	
	/**
	 * パスワードを更新するメソッド
	 * @param user
	 * @throws BusinessException
	 */
	@Override
	@Transactional
	public void updatePassword(User user) throws BusinessException {
		
		try {
			
			// ユーザIDが存在するか確認
			confirmUserId(user);
			
		}catch(BusinessException e) {
			
			// 業務エラーを投げる
			throw e;
			
		}
		
		// パスワードを登録
		userMapper.updateUserPassword(user);
		
	}
	
	
	/**
	 * 新規登録を行うメソッド
	 * @param user
	 * @throws BusinessException
	 */
	@Override
	@Transactional
	public void insertUser(User user) throws BusinessException {
		
		try {
			
			// ユーザ情報が適切か確認
			confirmUser(user);
			
		}catch(BusinessException e) {
			
			// 業務エラーを投げる
			throw e;
		}
		
		// 新規登録
		userMapper.insertUser(user);
		
	}

}

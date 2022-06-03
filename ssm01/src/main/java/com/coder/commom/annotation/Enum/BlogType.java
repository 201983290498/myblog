package com.coder.commom.annotation.Enum;

/**
 * 对博客进行分类
 * @author coder
 */

public enum BlogType {
  /**
   * 出发点，从专业技术层面分为工程，前沿新技术，基础理论
   */
  SoftwareTechnologies("工程技术"),
  AITechnologies("人工智能"),
  BasicTheories("基础理论"),

  /**
   * 生活方面关注个人的笔记,日记,一些热点话题
   */
  Article("生活随笔"),
  Diary("日记"),
  Topic("话题"),
  /**
   * 有待孵化的分类
   */
  Others("其他");

  private final String type;
  BlogType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }

  public static BlogType getBlogType(String type) {
    if("工程技术".equals(type)){
      return SoftwareTechnologies;
    }else if("人工智能".equals(type)){
      return AITechnologies;
    }else if("基础理论".equals(type)){
      return BasicTheories;
    }else if("生活随笔".equals(type)){
      return Article;
    }else if("日记".equals(type)){
      return Diary;
    }else if("话题".equals(type)){
      return Topic;
    }else{
      return Others;
    }
  }
}

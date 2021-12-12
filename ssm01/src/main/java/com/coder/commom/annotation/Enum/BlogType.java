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
    if(type.equals("工程技术")){
      return SoftwareTechnologies;
    }else if(type.equals("人工智能")){
      return AITechnologies;
    }else if(type.equals("基础理论")){
      return BasicTheories;
    }else if(type.equals("生活随笔")){
      return Article;
    }else if(type.equals("日记")){
      return Diary;
    }else if(type.equals("话题")){
      return Topic;
    }else{
      return Others;
    }
  }
}

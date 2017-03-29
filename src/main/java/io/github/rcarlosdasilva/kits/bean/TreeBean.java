package io.github.rcarlosdasilva.kits.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rits.cloning.Cloner;

/**
 * 树形结构Bean封装
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class TreeBean<B> implements Serializable {

  private static final long serialVersionUID = -750007363950239916L;

  private static final Cloner CLONER = new Cloner();

  private String sign;
  private int depth;
  private B object;
  private TreeBean<B> parent;
  private LinkedHashMap<String, TreeBean<B>> leaves;

  private TreeBean() {
    this(null);
  }

  private TreeBean(B object) {
    this.sign = UUID.randomUUID().toString();
    this.object = object;
  }

  public static <B> TreeBean<B> root() {
    TreeBean<B> root = new TreeBean<B>();
    root.depth = 0;
    return root;
  }

  public static <B> TreeBean<B> root(B object) {
    TreeBean<B> root = new TreeBean<B>(object);
    root.depth = 0;
    return root;
  }

  public int getDepth() {
    return depth;
  }

  public B getObject() {
    return object;
  }

  public List<TreeBean<B>> getLeaves() {
    if (leaves != null && leaves.size() > 0) {
      return ImmutableList.copyOf(leaves.values());
    }
    return null;
  }

  public synchronized void addLeave(B object) {
    if (this.leaves == null) {
      this.leaves = Maps.newLinkedHashMap();
    }

    TreeBean<B> bean = new TreeBean<B>(object);
    bean.parent = this;
    bean.depth = this.depth + 1;
    this.leaves.put(bean.sign, bean);
  }

  @SuppressWarnings("unchecked")
  public void addLeaves(B... objects) {
    addLeaves(Lists.newArrayList(objects));
  }

  public void addLeaves(Collection<B> objects) {
    if (objects != null) {
      for (B object : objects) {
        addLeave(object);
      }
    }
  }

  public boolean discard() {
    if (this.parent == null) {
      return false;
    }

    TreeBean<B> removed = this.parent.leaves.remove(this.sign);
    this.parent = null;
    return removed != null;
  }

  public TreeBean<B> copy() {
    return CLONER.deepClone(this);
  }

}

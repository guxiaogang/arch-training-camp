第八周主要涉及数据结构和算法、网络通信模型和数据库，比较基础，查缺补漏。
#### NP问题
P：能在多项式时间复杂度内解决的问题  
NP：能在多项式时间复杂度内验证答案正确与否的问题
NP-hard：NP问题的解法可以规约到NP-hard问题的解法  
NP完全问题：是一个NP-hard问题，也是一个NP问题

#### 红黑树VS平衡二叉树
红黑树最多3次旋转就会重新达成红黑平衡，时间复杂度O(1)  
在大量增删的情况下，红黑树的效率更高  
红黑树的平衡性不如平衡二叉树，查找效率要差一些

#### 常用算法
* 穷举算法
* 递归算法
* 贪心算法
* 动态规划

#### 数据库架构
连接器-->语法分析器-->语义分析与优化器-->执行引擎
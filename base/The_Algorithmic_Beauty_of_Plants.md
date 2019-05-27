
```
Created on  2019.05.24.15:44
The Algorithmic Beauty of Plants 翻译：Moly Chin
@author: molychin@qq.com
```
## The Algorithmic Beauty of Plant

|![](res/2019-05-27-11-19-21.png)!
|:---:|
|The Algorithmic Beauty of Plants|

Preface

The beauty of plants has attracted the attention of mathematicians for Mathematics centuries. Conspicuous geometric features such as the bilateral sym- and beauty metry of leaves, the rotational symmetry of flowers, and the helical arrangements of scales in pine cones have been studied most extensively. This focus is reflected in a quotation from Weyl [159, page 3],“Beauty is bound up with symmetry.”  
This book explores two other factors that organize plant structures and therefore contribute to their beauty. The first is the elegance and relative simplicity of developmental algorithms, that is, the rules which describe plant development in time. The second is self-similarity, characterized by Mandelbrot [95, page 34] as follows:
When each piece of a shape is geometrically similar to the whole, both the shape and the cascade that generate it are called self-similar.  
This corresponds with the biological phenomenon described by Herman,Lindenmayer and Rozenberg [61]:
In many growth processes of living organisms, especially of plants, regularly repeated appearances of certain multicellular structures are readily noticeable.... In the case of a compound leaf, for instance, some of the lobes (or leaflets),which are parts of a leaf at an advanced stage, have the same shape as the whole leaf has at an earlier stage.  
Thus, self-similarity in plants is a result of developmental processes. Growth and By emphasizing the relationship between growth and form, this book form follows a long tradition in biology. D’Arcy Thompson [143] traces its origins to the late seventeenth century, and comments:  
Organic form itself is found, mathematically speaking, to be a function of time.... We might call the form of an organism an event in space-time, and not merely a configuration in space.  
This concept is echoed by Hall´ e, Oldeman and Tomlinson [58]:  
The idea of the form implicitly contains also the history of such a form.

#### 序言

**数世纪以来，植物的美丽吸引了不少数学家的注意**。他们很广泛地研究了植物的显著几何特征，如叶片的双侧对称性、美感、花的旋转对称性以及松果中鳞片的螺旋排列等。这一焦点反映在Weyl的一句话中：“**美与对称紧密相连**”   
这本书探讨了组织植物结构的另外两个因素，因此这有助于了解他们的美丽。首先是发育算法的优雅和相对简单，即 **描述植物发育的规则**。第二个是 **自相似性**，其特征是如Mandelbrot描述：“当一个形状的每一组成部分在几何上与整体相似时，产生它的形状和级联都被称为自相似。”

这与Herman、Lindenmayer和Rozenberg描述的生物现象相对应：“在许多生物，尤其是植物的生长过程中，**某些多细胞结构的有规律的重复出现是显而易见的**……例如，在复叶的情况下，某些叶（或小叶）的形状与整个叶片在早期的形状相同，处于晚期阶段的叶子具有与整个叶子在早期阶段具有相同的形状。”

因此，**植物的自我相似性是发育过程的结果**。通过强调生长和形态之间的关系，这本书的形式遵循了生物学中的一个悠久传统。D'Arcy Thompson[143]追溯到17世纪晚期，并评论：“**从数学上讲，有机形式本身就是时间的函数。我们可以称有机体的形式为时空事件，而不仅仅是空间形态**。”
霍尔·E、奥尔德曼和汤姆林森也认同这一概念：**形式的概念隐含地包含了这种形式的历史**。

The developmental processes are captured using the formalism of Modeling of L-systems. They were introduced in 1968 by Lindenmayer [82] as a plants theoretical framework for studying the development of simple multicellular organisms, and subsequently applied to investigate higher plants and plant organs. After the incorporation of geometric features, plant models expressed using L-systems became detailed enough to allow the use of computer graphics for realistic visualization of plant structures and developmental processes.

The emphasis on graphics has several motivations. A visual comparison of models with real structures is an important component of model validation. The display of parameters and processes not observable directly in living organisms may assist in the analysis of their physiology, and thus present a valuable tool for educational purposes. From an aesthetic perspective, plants present a wealth of magnificent objects for image synthesis. The quest for photorealism challenges modeling and rendering algorithms, while a departure from realism may offer a fresh view of known structures.

The application of computer graphics to biological structures is only one of many factors that contribute to the interdisciplinary character of this book. For example, the notion of L-systems is a part of formal language theory, rooted in the theory of algorithms. The application of L-systems to plant description has been studied by biologists, and involves various methods of general mathematics. Self-similarity relates plant structures to the geometry of fractals. Computer-aided visualization of these structures, and the processes that create them, joins science with art.

利用L系统建模的形式化方法来捕获开发过程。林登迈耶（Lindenmayer）于1968年将其作为研究简单多细胞生物发育的植物理论框架引入，并随后应用于高等植物和植物器官的研究。在融合了几何特征之后，使用L系统表达的植物模型变得足够详细，可以使用计算机图形真实地可视化植物结构和发育过程。  
对图形的强调有几个动机。模型与实际结构的可视化比较是模型验证的重要组成部分。显示生物体中不能直接观察到的参数和过程，可能有助于对其生理学进行分析，从而为教育目的提供有价值的工具。从美学的角度来看，植物为图像合成提供了丰富的宏伟物体。对照相现实主义的探索挑战了建模和渲染算法，而背离现实主义可能会为已知结构提供新的视角。  
计算机图形学在生物结构中的应用只是促成这本书跨学科特点的众多因素之一。例如，L系统的概念是形式语言理论的一部分，植根于算法理论。生物学家研究了L-系统在植物描述中的应用，涉及到各种通用数学方法。自相似性将植物结构与分形几何联系起来。计算机辅助可视化这些结构，以及创造它们的过程，将科学与艺术结合起来。

About the book The study of an area that combines so many disciplines is very stimulating. Some results may be of special interest to students of biology or computer graphics, but a much wider circle of readers, generally interested in science, may find mathematical plant models inspiring, and the open problems worth further thought. Consequently, all basic concepts are presented in a self-contained manner, assuming only general knowledge of mathematics at the junior college level.

This book focuses on original research results obtained by the authors in the scope of the cooperation between the Theoretical Biology Group, directed by Aristid Lindenmayer at the University of Utrecht, and the Computer Graphics Group, working under the supervision of Przemyslaw Prusinkiewicz at the University of Regina. Technically, the book evolved from the SIGGRAPH ’88 and ’89 course notes Lindenmayer systems, fractals, and plants, published by Springer-Verlag in the series Lecture Notes in Biomathematics [112]. The present volume has been extended with edited versions of recent journal and conference papers (see Sources), as well as previously unpublished results.

Aristid Lindenmayer is the author of the notion of L-systems which forms the main thread of the book. He also played an essential role in the reported research by suggesting topics for study, guiding the construction of specific plant models, monitoring their correctness and participating in many discussions of biological and mathematical problems. Seriously ill, Professor Lindenmayer co-authored and edited several chapters, but was not able to participate in the completion of this work. If any inaccuracies or mistakes remain, he could not prevent them. Still, in spite of unavoidable shortcomings, we hope that this book will convey his and our excitement of applying mathematics to explore the beauty of plants.

关于这本书，对一个结合了这么多学科的领域的研究是非常令人兴奋的。一些结果可能对生物学或计算机图形学的学生特别感兴趣，但更广泛的读者圈，一般对科学感兴趣，可能会发现数学植物模型鼓舞人心，而开放性问题值得进一步思考。因此，所有基本概念都以独立的方式呈现，假设只有普通大学水平的数学知识。  
这本书集中在作者在乌得勒支大学阿里斯蒂德·林登迈耶（Aristid Lindenmayer）领导的理论生物学小组和在里贾纳大学Przemyslaw Prusinkiewicz监督下工作的计算机图形学小组之间的合作范围内获得的原始研究成果。从技术上讲，这本书是由斯普林格·维拉格在《生物数学系列讲座笔记》（112）中发表的《Siggraph'88和'89课程笔记》、《Lindenmayer Systems、Fractals和Plants》演变而来。本卷扩展了近期期刊和会议论文的编辑版本（见资料来源），以及以前未发表的结果。  
Aristid Lindenmayer是L系统概念的作者，L系统是本书的主线。他还通过提出研究主题、指导具体植物模型的构建、监测其正确性以及参与许多生物学和数学问题的讨论，在报告的研究中发挥了重要作用。林登迈耶教授病得很重，合著并编辑了几章，但未能参与完成这项工作。如果仍有任何不准确或错误，他就无法阻止。尽管有不可避免的缺点，我们还是希望这本书能传达出他和我们运用数学探索植物之美的兴奋之情。






>continue...

package heap.leftist;

import heap.Element;
import heap.EmptyHeapException;
import heap.Heap;

import java.util.LinkedList;

public class LeftistHeap<T extends Comparable<T>> implements Heap<T> {
    private LeftistElement<T> root; //reference of root

    /**
     * Constructor of LeftistHeap
     */
    public LeftistHeap() {
        this.root = null;
    }


    /**
     * Mutator to insert a LeftistElement in the LeftistHeap
     *
     * @param value: Value of the new LeftistElement
     * @return reference of the new LeftistElement
     */
    @Override
    public Element<T> insert(T value) {
        LeftistElement<T> leftistElement = new LeftistElement<>(value, this);
        add(leftistElement);
        return leftistElement;
    }

    /**
     * Utility mutator for adding a new Element
     *
     * @param element: reference of LeftistElement that needs to be added
     */
    public void add(LeftistElement<T> element) {
        if (root == null) {
            root = element;
        } else {
            root = mergeHeap(element, root);
        }
    }

    /**
     * Getter of smallest LeftistElement of LeftistHeap => root
     *
     * @return reference of the smallest LeftistElement
     * @throws EmptyHeapException: if root is NULL
     */
    @Override
    public Element<T> findMin() throws EmptyHeapException {
        if (root == null) {
            throw new EmptyHeapException();
        } else {
            return root;
        }
    }

    /**
     * Mutator to remove the smallest LeftistElement of the LeftistHeap
     *
     * @return Value of the smallest LeftistElement before deletion
     * @throws EmptyHeapException: if root is NULL
     */
    @Override
    public T removeMin() throws EmptyHeapException {
        if (root == null) {
            throw new EmptyHeapException();
        } else {
            LeftistElement<T> minimumElement = root;
            root = mergeHeap(root.getLeftChild(), root.getRightChild());
            if (root != null) {
                root.setParent(null);
                minimumElement.setRightChild(null);
                minimumElement.setLeftChild(null);
                minimumElement.setParent(null);

            }
            return minimumElement.value();
        }
    }

    /**
     * Mutator to delete a LeftistElement in the LeftistHeap
     *
     * @param element: reference of the LeftistElement that will be deleted
     */
    void deleteElement(LeftistElement<T> element) {

        bubbleUp(element, true); //let the element bubble up to root

        try {
            removeMin(); //Remove the root (in this case it will not be the smallest element
        } catch (EmptyHeapException e) {
            e.printStackTrace();
        }


    }


    /**
     * Mutator to merge two roots of LeftistHeaps
     *
     * @param root1: root of LeftistHeap 1
     * @param root2: root of LeftistHeap 2
     * @return reference of the new root
     */
    private LeftistElement<T> mergeHeap(LeftistElement<T> root1, LeftistElement<T> root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        //Select the smallest root
        if (root2.value().compareTo(root1.value()) < 0) {
            LeftistElement<T> tmp = root1;
            root1 = root2;
            root2 = tmp;
        }

        //let the right child of the smallest heap merge with with the other root
        LeftistElement<T> tmp = mergeHeap(root1.getRightChild(), root2);

        tmp.setParent(root1);
        root1.setRightChild(tmp);

        //restore the LeftistHeap structure when necessary
        if (!root1.hasLeftChild()) {
            swapChildren(root1);
        } else {
            if (root1.getLeftChild().getNpl() < root1.getRightChild().getNpl()) {
                swapChildren(root1);
            }
            root1.setNpl(root1.getRightChild().getNpl() + 1);
        }
        return root1;
    }


    /**
     * Mutator to swap two children of a parent
     *
     * @param parent: reference of the parent
     */
    private void swapChildren(LeftistElement<T> parent) {
        LeftistElement<T> tmp = parent.getLeftChild();
        parent.setLeftChild(parent.getRightChild());
        parent.setRightChild(tmp);
    }


    /**
     * Recursive function to restore the heap when necessary. If the parent has a greater value than the LeftistElement,
     * swap them and call the bubbleUp again on the same LeftistElement.
     *
     * @param element: reference of the element that needs to bubbleUp
     * @param toRoot:  true -> element will become the root of the tree
     *                 false -> normal bubbleUp
     */
    void bubbleUp(LeftistElement<T> element, boolean toRoot) {
        LeftistElement<T> parent = element.getParent();
        if (parent != null) {
            if (element.value().compareTo(parent.value()) <= 0 || toRoot) {
                swapChildParent(element, parent);
                if (element.getParent() == null) {
                    this.root = element;
                } else {
                    bubbleUp(element, toRoot);
                }
            }
        }
    }

    /**
     * Analogue at the bubbleUp function, only now we will evaluate the LeftistElement with his children.
     *
     * @param element: reference of the LeftistElement that has to bubbleDown
     */
    void bubbleDown(LeftistElement<T> element) {
        LeftistElement<T> leftChild, rightChild;
        LeftistElement<T> minIndex;
        leftChild = element.getLeftChild();
        rightChild = element.getRightChild();

        if (rightChild == null) {
            if (leftChild != null) {
                minIndex = leftChild;
            } else {
                return;
            }
        } else {
            if (leftChild != null) {
                if (leftChild.value().compareTo(rightChild.value()) <= 0) {
                    minIndex = leftChild;
                } else {
                    minIndex = rightChild;
                }
            } else {
                return;
            }
        }


        if (minIndex.value().compareTo(element.value()) < 0) {
            swapChildParent(minIndex, element);
            bubbleDown(element);
        }

    }

    /**
     * Mutator to swap Child with Parent and set all his references
     *
     * @param child:  reference of the child
     * @param parent: reference of the parent
     */
    private void swapChildParent(LeftistElement<T> child, LeftistElement<T> parent) {
        LeftistElement<T> child_LeftChild = child.getLeftChild(); //Can be null
        LeftistElement<T> child_RightChild = child.getRightChild(); //Can be null
        LeftistElement<T> parent_otherChild;

        if (parent.getRightChild() == child) {
            parent_otherChild = parent.getLeftChild();
        } else {
            parent_otherChild = parent.getRightChild();
        }

        LeftistElement<T> parent_Parent = parent.getParent();

        //swap child and parent
        int npl_child = child.getNpl();
        if (parent.getRightChild() == child) {
            child.setRightChild(parent);
            child.setLeftChild(parent_otherChild);
        } else {
            child.setLeftChild(parent);
            child.setRightChild(parent_otherChild);
        }
        child.setParent(parent_Parent);
        child.setNpl(parent.getNpl());

        parent.setParent(child);
        parent.setNpl(npl_child);
        parent.setLeftChild(child_LeftChild);
        parent.setRightChild(child_RightChild);

        //Set the correct child of the upper parent (parent_Parent)
        if (parent_Parent != null) {
            if (parent_Parent.getRightChild() == parent) {
                parent_Parent.setRightChild(child);
            } else {
                parent_Parent.setLeftChild(child);
            }
        } else {
            root = child;
        }

        //Set the correct parent for the child's children
        if (child_LeftChild != null) {
            child_LeftChild.setParent(parent);
        }
        if (child_RightChild != null) {
            child_RightChild.setParent(parent);
        }

        //set the correct parent for the parent_OtherChild
        if (parent_otherChild != null) {
            parent_otherChild.setParent(child);

        }

    }


    public void displayLeftistTree(LeftistElement<T> curr) {
        LinkedList<LeftistElement<T>> parent = new LinkedList<>();
        LinkedList<LeftistElement<T>> child = new LinkedList<>();
        LinkedList<LeftistElement<T>> newchild = new LinkedList<>();

        LeftistElement<T> temp = curr;
        int level = 1;

        //add root to parent list and its children to the child list
        if (temp != null) {
            parent.add(temp);
            if (temp.hasLeftChild()) {
                child.add(temp.getLeftChild());
            }
            if (temp.hasRightChild()) {
                child.add(temp.getRightChild());
            }

        }

        while (parent != null) {
            System.out.println();
            System.out.print("Level " + level++ + ": [");
            int index;
            //Display nodes of the current level
            for (index = 0; index < parent.size() - 1; index++) {
                LeftistElement<T> tmp = parent.get(index);
                System.out.print("(Parent:" + tmp.getParent().value() + " NPL: " + tmp.getNpl() + " Value: " + tmp.value() + "),");
            }
            if (index == 0) {
                System.out.print(parent.get(index).value());
                System.out.print("]");
            } else {
                System.out.print(" (Parent: " + parent.get(index).getParent().value() + " NPL: " + parent.get(index).getNpl() + " Value: " + parent.get(index).value());
                System.out.print("]");
            }

            //if nodes at last level are displayed then exit
            if (child.size() == 0 && newchild.size() == 0)
                break;

            //get the child nodes of the child list
            newchild = new LinkedList<>();
            for (int j = 0; j < child.size(); j++) {
                if (child.get(j).hasLeftChild())
                    newchild.add(child.get(j).getLeftChild());
                if (child.get(j).hasRightChild())
                    newchild.add(child.get(j).getRightChild());
            }
               /*assign current childlist to the parent list and the current
			     newchild list becomes the new child list*/
            parent = child;
            child = newchild;

        }
        System.out.println();
    }
}

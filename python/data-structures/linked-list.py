class LinkedListNode:
    def __init__(self):
        self.data = None
        self.next = None

    def str(self):
        print("Node: {}".format(self.data))


class LinkedList:
    def __init__(self):
        self.head = None

    def add(self, data):
        print("Add Node: {}".format(data))

        node = LinkedListNode()
        node.data = data

        if self.head is None:
            self.head = node
            return

        current = self.head
        while current.next is not None:
            current = current.next

        current.next = node

    def str(self):
        if self.head is not None:
            print("Printing LinkedList")

            current = self.head
            while current is not None:
                current.str()
                current = current.next


def run():
    ll = LinkedList()

    for i in range(5):
        ll.add(i)

    ll.str()


run()

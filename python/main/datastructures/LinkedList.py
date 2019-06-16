class LinkedListNode:
    def __init__(self, data):
        self.data = data
        self.next = None

    def str(self):
        print("Node: {}".format(self.data))


class LinkedList:
    def __init__(self):
        self.head = None

    def append(self, data):
        print("append: {}".format(data))
        node = LinkedListNode(data)

        if self.head is None:
            self.head = node
            return

        current = self.head
        while current.next is not None:
            current = current.next

        current.next = node

    def prepend(self, data):
        print("prepend: {}".format(data))
        node = LinkedListNode(data)

        node.next = self.head
        self.head = node

    def add_at(self, index, data):
        print("add_at {}: {}".format(index, data))
        if self is not None:
            if index == 1:
                self.prepend(data)
                return

            node = LinkedListNode(data)

            current = self.head.next
            current_index = 2
            prev = self.head

            while current is not None and current_index < index:
                temp = current.next
                prev = current
                current = temp
                current_index += 1

            if current_index == index:
                prev.next = node
                node.next = current

    def reverse(self):
        print("reverse:")

        if self.head is not None:
            prev = self.head
            current = prev.next
            next = current.next

            prev.next = None
            while current is not None:
                current.next = prev

                prev = current
                current = next

                if next is not None:
                    next = next.next

            self.head = prev

    def reverse_recursive(self):
        print("reverse_recursive:")

        if self.head is not None:
            old_head = self.head
            self.head = self.reverse_recursive_helper(self.head)
            old_head.next = None

    def reverse_recursive_helper(self, current_head):
        print("reverse_recursive_helper: {}".format(current_head.data))

        if current_head.next is None:
            return current_head

        new_head = self.reverse_recursive_helper(current_head.next)

        current_head.next.next = current_head

        return new_head

    def get_last_node(self):
        print("get_last_node:")

        if self.head is None:
            return None

        current = self.head
        while current.next is not None:
            current = current.next

        print("Last Node: {}".format(current.data))
        return current

    def get_last_node_recursive(self):
        print("get_last_node_recursive:")

        if self.head is None:
            return None

        last = self.get_last_helper_recursive(self.head)
        print("Last Node: {}".format(last.data))

        return last

    def get_last_helper_recursive(self, current_head):
        print("get_last_helper_recursive: {}".format(current_head.data))

        if current_head.next is None:
            return current_head

        return self.get_last_helper_recursive(current_head.next)

    def get_first_node(self):
        print("get_first_node:")
        return self.head

    def size(self):
        sz = 0

        current = self.head
        while current is not None:
            current = current.next
            sz += 1

        return sz

    def str(self):
        if self.head is not None:
            print("> LinkedList size: {}".format(self.size()))

            current = self.head
            while current is not None:
                current.str()
                current = current.next

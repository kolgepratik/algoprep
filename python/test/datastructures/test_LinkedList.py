import unittest

from python.main.datastructures.LinkedList import LinkedList


class LinkedListTest(unittest.TestCase):
    def test_append(self):
        linked_list = LinkedList()

        # Act
        linked_list.append(1)

        self.assertIsNotNone(linked_list.get_first_node())
        self.assertEqual(1, linked_list.size())
        self.assertEqual(1, linked_list.get_first_node().data)

    def test_prepend(self):
        linked_list = LinkedList()

        linked_list.append(1)
        self.assertEqual(1, linked_list.get_first_node().data)

        # Act
        linked_list.prepend(2)

        self.assertEqual(2, linked_list.get_first_node().data)

    def test_get_first_node(self):
        linked_list = LinkedList()

        linked_list.append(1)
        linked_list.append(2)
        linked_list.append(3)

        # Act
        first_node = linked_list.get_first_node()

        self.assertIsNotNone(first_node)
        self.assertEqual(1, first_node.data)

    def test_get_last_node(self):
        linked_list = LinkedList()

        linked_list.append(1)
        linked_list.append(2)
        linked_list.append(3)

        # Act
        last_node = linked_list.get_last_node()

        self.assertIsNotNone(last_node)
        self.assertEqual(3, last_node.data)

    def test_get_last_node_recursive(self):
        linked_list = LinkedList()

        linked_list.append(1)
        linked_list.append(2)
        linked_list.append(3)

        # Act
        last_node = linked_list.get_last_node_recursive()

        self.assertIsNotNone(last_node)
        self.assertEqual(3, last_node.data)

    def test_reverse(self):
        linked_list = LinkedList()

        linked_list.append(1)
        linked_list.append(2)
        linked_list.append(3)
        linked_list.append(4)

        self.assertEqual(4, linked_list.size())
        self.assertEqual(1, linked_list.get_first_node().data)
        self.assertEqual(4, linked_list.get_last_node().data)

        # Act
        linked_list.reverse()

        self.assertEqual(4, linked_list.size())
        self.assertEqual(4, linked_list.get_first_node().data)
        self.assertEqual(1, linked_list.get_last_node().data)

    def test_reverse_recursive(self):
        linked_list = LinkedList()

        linked_list.append(1)
        linked_list.append(2)
        linked_list.append(3)
        linked_list.append(4)

        self.assertEqual(4, linked_list.size())
        self.assertEqual(1, linked_list.get_first_node().data)
        self.assertEqual(4, linked_list.get_last_node().data)

        # Act
        linked_list.reverse_recursive()

        self.assertEqual(4, linked_list.size())
        self.assertEqual(4, linked_list.get_first_node().data)
        self.assertEqual(1, linked_list.get_last_node().data)


if __name__ == '__main__':
    unittest.main()

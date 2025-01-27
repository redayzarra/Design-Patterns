from abc import ABC, abstractmethod
import copy


# Create a Prototype pattern: Define an abstract class for clones
class Prototype(ABC):
    @abstractmethod
    def clone(self):
        pass


# Define a concrete class for creating documents
class Document(Prototype):
    def __init__(self, content: str):
        self.content = content

    def clone(self):
        return Document(self.content)

# Create an original document
original_document = Document("This is the first version of the document.")
print("Original Document Content:", original_document.content)

# Clone the original document
cloned_document = original_document.clone()
print("Cloned Document Content:", cloned_document.content)

# Modify the content of the original document
original_document.content = "This is the second version of the document."
print("Modified Original Document Content:", original_document.content)
print("Cloned Document Content After Modification:", cloned_document.content)

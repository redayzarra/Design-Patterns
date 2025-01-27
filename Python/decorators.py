def my_decorator(func):
    def wrapper(*args, **kwargs):
        print("Something before the function.")
        result = func(*args, **kwargs)
        print("Something after the function.\n\n")
        return result
    return wrapper

@my_decorator
def say_hello():
    print("Hello!")

say_hello()

class MyDecorator:
    def __init__(self, func):
        self.func = func

    def __call__(self, *args, **kwargs):
        print("Something before the function.")
        result = self.func(*args, **kwargs)
        print("Something after the function.")
        return result

@MyDecorator
def say_hi():
    print("Hi!")

say_hi()

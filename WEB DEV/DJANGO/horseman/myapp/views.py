from django.shortcuts import render
from .models import post

# Create your views here.
def index(request):
    return render(request,'index.html')

def blog(request):
    posts = post.objects.all()
    return render(request,'blog.html',{'posts':posts})

def blogView(request,pk):
    posts = post.objects.get(id=pk)
    return render(request,'blogView.html',{'posts':posts})
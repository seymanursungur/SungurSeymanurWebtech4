# -*- coding: utf-8 -*-
from __future__ import unicode_literals

import pytz
from django.shortcuts import render

# Import Datetime class
from datetime import datetime

def index(request):

    myDate = datetime.now(pytz.timezone('Europe/London'))
  
    return render(request, 'template.html', {
        'date': myDate

    })


    $(document).ready(function(){
        $('.action').on('click',function(){
            var id = $(this).attr('id');
            if (this.textContent == 'Изменить') {
		    this.textContent = 'Сохранить';
		    $('.note__body_'+id).attr('contenteditable','true');
		    $('.note__body_'+id).focus();
	    }
	    else {
		    this.textContent = 'Изменить';
		    $('.note__body_'+id).attr('contenteditable','false');
		    var value = $('.note__body_'+id).text();
		    value = value.replace(/[a-za-яё]/gi,'');
		    value = value.replace(",",".");
		    window.location.href = "/update/?id="+id+"&value="+value;
	    }
        });
    });
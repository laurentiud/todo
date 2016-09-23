//create todo
$('.add-todo').on('keypress',function (e) {
      e.preventDefault
      if (e.which == 13) {
           if($(this).val() != ''){
           var todo = $(this).val();
           var hash = $(this).attr('id');
            createTodo(hash, todo);
           }else{
               // some validation
           }
      }
});

// mark task as done
$('.todolist').on('change','#sortable li input[type="checkbox"]',function(){
    if($(this).prop('checked')){
        var doneItem = $(this).parent().parent().find('label').text();
        $(this).parent().parent().parent().addClass('remove');
        $.ajax({
            url: '/todo/delete/' + $(this).attr('id'),
            type: 'DELETE',
            success: function(result) {}
        });
        done(doneItem);
    }
});

//create task
function createTodo(hash, text){
    $.ajax({
        url: '/todo/' + hash + '/' + text,
        type: 'PUT',
        success: function(result) {
            var markup = '<li class="ui-state-default"><div class="checkbox"><label><input type="checkbox" value="" id="'+result+'"/>'+ text +'</label></div></li>';
            $('#sortable').append(markup);
            $('.add-todo').val('');
        }
    });
}

//mark task as done
function done(doneItem){
    var done = doneItem;
    $('.remove').remove();
}